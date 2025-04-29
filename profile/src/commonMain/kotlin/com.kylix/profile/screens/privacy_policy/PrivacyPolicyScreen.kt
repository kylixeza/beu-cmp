package com.kylix.profile.screens.privacy_policy

import beukmm.base.BaseWebViewScreen
import cafe.adriel.voyager.core.lifecycle.ScreenDisposable

class PrivacyPolicyScreen: BaseWebViewScreen(
    title = "Privacy Policy",
    url = "https://beu-api.up.railway.app/privacy-policy"
), ScreenDisposable