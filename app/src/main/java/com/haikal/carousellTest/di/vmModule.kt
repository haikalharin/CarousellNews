package com.haikal.carousellTest.di

import com.haikal.carousellTest.presentation.news.NewsViewModel

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.scope.get
import org.koin.dsl.module


val vmModule = module {
    viewModel { NewsViewModel() }
}