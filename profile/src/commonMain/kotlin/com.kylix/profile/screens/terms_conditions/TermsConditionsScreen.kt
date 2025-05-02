package com.kylix.profile.screens.terms_conditions

import beukmm.base.BaseWebViewScreen
import cafe.adriel.voyager.core.lifecycle.ScreenDisposable
import com.kylix.profile.BuildKonfig

class TermsConditionsScreen: BaseWebViewScreen(
    title = "Terms and Conditions",
    url = "${BuildKonfig.baseUrl}terms-and-conditions"
), ScreenDisposable