package com.kylix.splash.screen

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import co.touchlab.kermit.Logger
import com.kylix.core.repositories.splash.SplashRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SplashScreenModel(
    private val splashRepository: SplashRepository
): ScreenModel {

    private var isPassOnBoarding = MutableStateFlow(false)

    private var isTokenExist = MutableStateFlow(false)

    var route = MutableStateFlow<Route?>(null)
        private set

    init {
        screenModelScope.launch {
            isPassOnBoarding.value = splashRepository.isOnBoardingPassed()
            isTokenExist.value = splashRepository.isTokenExist()

            if (isPassOnBoarding.value) {
                if (isTokenExist.value) {
                    route.value = Route.MAIN
                } else {
                    route.value = Route.LOGIN
                }
            } else {
                route.value = Route.ONBOARD
            }
        }
    }
}

enum class Route {
    ONBOARD, LOGIN, MAIN
}