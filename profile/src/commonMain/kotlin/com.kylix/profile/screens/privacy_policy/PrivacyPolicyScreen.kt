package com.kylix.profile.screens.privacy_policy

import beukmm.base.BaseWebViewScreen
import cafe.adriel.voyager.core.lifecycle.ScreenDisposable
import com.kylix.profile.BuildKonfig

class PrivacyPolicyScreen: BaseWebViewScreen(
    title = "Privacy Policy",
    url = "${BuildKonfig.baseUrl}privacy-policy"
), ScreenDisposable