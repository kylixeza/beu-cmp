package com.kylix.core.repositories

import com.kylix.core.data.local.BeuDataStore

class SplashRepositoryImpl(
    private val beuDataStore: BeuDataStore
): SplashRepository {

    override suspend fun passOnBoarding() {
        beuDataStore.saveIsPassOnBoarding(true)
    }

    override suspend fun isOnBoardingPassed(): Boolean {
        return beuDataStore.getIsPassOnBoarding()
    }
}