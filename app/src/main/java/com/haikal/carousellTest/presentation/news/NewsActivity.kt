package com.haikal.carousellTest.presentation.news

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.haikal.carousellTest.R
import com.haikal.carousellTest.R.id.nav_popular
import com.haikal.carousellTest.R.id.nav_recent
import com.haikal.carousellTest.databinding.ActivityNewsBinding
import com.haikal.carousellTest.utils.gone
import com.paem.core.data.remote.RequestState
import com.paem.core.entities.News
import com.paem.core.utils.getRelativeTimeSpan
import com.paem.core.utils.handleErrorState
import com.paem.core.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter
    private val vm: NewsViewModel by viewModel()

    private val binding by lazy { ActivityNewsBinding.inflate(layoutInflater) }
    private lateinit var drawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)


        newsAdapter = NewsAdapter()
        observeViewModel()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            nav_recent -> {
                observeViewModel()
                true
            }

            nav_popular -> {
                observeViewModel(1)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logoutUser() {
        // Clear user session, redirect to login, etc.
        Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()

        val sharedPreferences = getSharedPreferences("MySharedPref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        val intent = Intent(this, NewsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun observeViewModel(type: Int = 0) = with(binding) {
        vm.getNews { state ->
            when (state) {
                RequestState.Loading -> progressBar.show()
                is RequestState.Success -> {
                    progressBar.gone()
                    if (type == 0) {
                        val sortedRecent =
                            state.result.sortedBy { getRelativeTimeSpan(it.timeCreated.toLong()) }
                        showNewsList(sortedRecent)
                    } else if (type == 1) {
                        val sortedPopular =
                            state.result.sortedWith(compareBy<News> { it.rank }.thenBy {
                                getRelativeTimeSpan(it.timeCreated.toLong())
                            })
                        showNewsList(sortedPopular)
                    } else {
                        showNewsList(state.result)
                    }
                }

                is RequestState.Failed -> {
                    progressBar.gone()
                    handleErrorState(state.error)
                }
            }
        }
    }

    private fun showNewsList(newsList: List<News>) {
        with(binding) {
            newsAdapter.addItems(newsList)
            recyclerView.apply {
                layoutManager = GridLayoutManager(this@NewsActivity, 1)
                adapter = newsAdapter
            }
        }
    }
}
