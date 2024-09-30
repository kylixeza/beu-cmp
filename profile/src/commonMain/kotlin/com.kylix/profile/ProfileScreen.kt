package com.kylix.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.components.SecondaryAppBar
import beukmm.di.koinScreenModel
import beukmm.navigator.SharedScreen
import beukmm.theme.White
import beukmm.util.customKamelConfig
import cafe.adriel.voyager.core.registry.rememberScreen
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kylix.profile.components.ItemSetting
import com.kylix.profile.screens.UpdateProfileScreen
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig

class ProfileScreen: Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<ProfileScreenModel>()
        val profileState by screenModel.profileState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow
        val loginScreen = rememberScreen(SharedScreen.Login)

        BaseScreenContent(
            topBar = {
                SecondaryAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Profile",
                    leftIcon = null,
                )
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(
                    start = 24.dp,
                    end = 24.dp,
                    top = 12.dp,
                    bottom = 92.dp
                )
            ) {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CompositionLocalProvider(LocalKamelConfig provides customKamelConfig) {
                            val painter = asyncPainterResource(profileState.avatar)
                            KamelImage(
                                resource = painter,
                                contentDescription = "Profile Picture",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.size(100.dp).clip(CircleShape)
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = profileState.username,
                            style = MaterialTheme.typography.bodyLarge.copy()
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }

                items(profileState.sections) { section ->
                    Text(
                        text = section.sectionName,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(vertical = 16.dp)
                    )
                    section.settings.forEach { setting ->
                        ItemSetting(
                            setting = setting,
                            onClick = {
                                navigator.push(UpdateProfileScreen())
                            }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { navigator.replace(loginScreen) }
                    ) {
                        Text(
                            text = "Logout",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = White
                            )
                        )
                    }
                }
            }
        }

    }
}