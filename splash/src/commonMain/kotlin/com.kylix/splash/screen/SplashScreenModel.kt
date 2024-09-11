package com.kylix.splash.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.kylix.core.repositories.splash.SplashRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenModel(
    private val splashRepository: SplashRepository
): ScreenModel {

    var isPassOnBoarding = MutableStateFlow(false)
        private set

    init {
        screenModelScope.launch {
            isPassOnBoarding.value = splashRepository.isOnBoardingPassed()
        }
    }

}