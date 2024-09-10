package com.kylix.core.repositories

interface SplashRepository {

    suspend fun passOnBoarding()

    suspend fun isOnBoardingPassed(): Boolean

}