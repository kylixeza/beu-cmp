package com.kylix.onboard.screen

import beukmm.base.BaseScreenModel
import beukmm.models.OnBoardContent
import cafe.adriel.voyager.core.model.screenModelScope
import com.kylix.core.repositories.SplashRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OnBoardScreenModel(
    private val splashRepository: SplashRepository
): BaseScreenModel() {

    val onBoardContent = MutableStateFlow(
        listOf(
            OnBoardContent(
                "Cook Guidance",
                "Find cooking tutorials that match your desired category",
                "files/onboard1.json"
            ),
            OnBoardContent(
                "User Discussion",
                "You can share your cooking experience with other users",
                "files/onboard2.json"
            ),
            OnBoardContent(
                "Image Recognition (Beta)",
                "You can search for recipes by pointing the camera at a food item.",
                "files/onboard3.json"
            ),
        )
    ).asStateFlow()

    fun passOnBoarding() {
        screenModelScope.launch {
            onSuspendProcess { splashRepository.passOnBoarding() }
            onDataSuccess()
        }
    }

}