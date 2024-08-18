package com.haikal.carousellTest.presentation.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.haikal.carousellTest.R
import com.haikal.carousellTest.databinding.ItemNewsBinding
import com.paem.core.entities.News
import com.paem.core.utils.getRelativeTimeSpan

class NewsAdapter() :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val itemList = mutableListOf<News>()

    fun addItems(itemList: List<News>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemNewsBinding =
            ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemNewsBinding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = itemList[position]
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News) {
            with(binding) {
                Glide.with(binding.root)
                    .load(data.bannerUrl)
                    .into(newsImage)
                title.text = data.title
                subtitle.text = data.description
                val timestamp = data.timeCreated.toLong()
                newsDate.text = getRelativeTimeSpan(timestamp)
            }
        }
    }
}

