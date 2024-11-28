# About

## What is Beu?
Beu (Bring Out The Best Chef In You) is a mobile application (both android and ios) that provides various Indonesian recipes including guides and videos. You can share your cooking results with others. The app also implements machine learning (image classification) to classify Indonesian food and will provide results in the form of the name of the food and other recipes.

## KMM Project Configurations
This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

# Ecosystem
1. Backend : https://github.com/kylixeza/beu-remaster-backend
2. Notebook: https://github.com/kylixeza/beu-remaster-ml

# Screenshots
<p>
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/1_splash.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/2_onboard-1.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/3_onboard-2.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/4_onboard-3.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/5_login.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/6_register.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/7_home.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/8_search.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/9_search.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/10_detail-1.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/11_detail-2.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/12_detail-3.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/13_review.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/14_camera-1.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/15_camera-2.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/16_profile.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/17_update_profile.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/18_reset_password.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/19_histories-1.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/20_histories-2.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/21_favorites.jpeg" >
<img width=200 src="https://raw.githubusercontent.com/kylixeza/beu-cmp/refs/heads/development/screenshots/22_help_center.jpeg" >
</p>

# Note
1. The classification feature only works on android, while on ios it still doesn't because the developer doesn't have a real device for testing.
2. You can create an account to use the app and it's free (no API Key)

# Contribution
For anyone who wants to contribute can send a pull request

Support Kylix to create another amazing projects!

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://buymeacoffee.com/kylixeza)


# Contact
<a href="https://linkedin.com/in/kylix-eza-saputra-1bb1b7192" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="kylix-eza-saputra-1bb1b7192" height="30" width="40" /></a>
<a href="https://instagram.com/k_ylix" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/instagram.svg" alt="k_ylix" height="30" width="40" /></a>
<a href="https://discord.gg/#Kylix3272" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/discord.svg" alt="#Kylix3272" height="30" width="40" /></a>
</p>
