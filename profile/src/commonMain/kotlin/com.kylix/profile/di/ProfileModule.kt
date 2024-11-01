package com.kylix.profile.di

import com.kylix.profile.ProfileScreenModel
import com.kylix.profile.screens.favorite.FavoriteScreenModel
import com.kylix.profile.screens.help_center.HelpCenterScreenModel
import com.kylix.profile.screens.history.HistoryScreenModel
import com.kylix.profile.screens.update_profile.UpdateProfileScreenModel
import org.koin.dsl.module

val profileModule = module {
    factory { ProfileScreenModel(get(), get()) }
    factory { UpdateProfileScreenModel(get()) }
    factory { FavoriteScreenModel(get()) }
    factory { HistoryScreenModel(get(), get()) }
    factory { HelpCenterScreenModel (get()) }
}