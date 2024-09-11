package com.kylix.core.repositories.splash

interface SplashRepository {

    suspend fun passOnBoarding()

    suspend fun isOnBoardingPassed(): Boolean

}