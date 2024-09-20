package com.kylix.review.di

import com.kylix.review.ReviewScreenModel
import org.koin.dsl.module

val reviewModule = module {
    factory { ReviewScreenModel(get()) }
}