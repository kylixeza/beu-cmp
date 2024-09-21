package com.kylix.home.di

import com.kylix.home.HomeScreenModel
import com.kylix.home.screens.category.CategoryScreenModel
import com.kylix.home.screens.search.SearchScreenModel
import org.koin.dsl.module

val homeModule = module {
    factory { HomeScreenModel(get(), get()) }
    factory { SearchScreenModel(get()) }
    factory { CategoryScreenModel(get()) }
}