package com.kylix.review

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import beukmm.base.BaseScreenContent
import beukmm.common.generated.resources.Res
import beukmm.common.generated.resources.ic_take_picture
import beukmm.components.BaseAppBar
import beukmm.components.BeuBasicTextField
import beukmm.components.LocalFlexboxImages
import beukmm.theme.Primary500
import beukmm.theme.White
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.kylix.review.components.DottedBorderRectangle
import com.kylix.review.components.RatingBarReview
import network.chaintech.cmpimagepickncrop.CMPImagePickNCropDialog
import network.chaintech.cmpimagepickncrop.imagecropper.rememberImageCropper
import org.jetbrains.compose.resources.painterResource

class ReviewScreen(
    private val historyId: String,
) : Screen {

    @Composable
    override fun Content() {

        val screenModel = koinScreenModel<ReviewScreenModel>()
        val reviewState by screenModel.reviewState.collectAsState()
        val uiState by screenModel.uiState.collectAsState()

        val navigator = LocalNavigator.currentOrThrow

        BaseScreenContent(
            topBar = {
                BaseAppBar(
                    "Review",
                    rightIcon = null,
                    onLeftIconClick = { navigator.pop() },
                )
            },
            uiState = uiState,
            onLoadingDialogDismissRequest = {
                screenModel.onFinishLoading()
            }
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(innerPadding),
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
            ) {
                item {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "How was your experience?",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    RatingBarReview(
                        modifier = Modifier.fillMaxWidth(),
                        maxRating = 5,
                        onRatingChanged = {
                            screenModel.setStars(it)
                        }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Write down your review",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    BeuBasicTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = reviewState.comment,
                        onValueChange = { screenModel.setComment(it) },
                        label = "Write your review here",
                        minLines = 5,
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Take a picture of your cooking!",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    LocalFlexboxImages(
                        images = reviewState.images,
                        onDeleteImage = {
                            screenModel.removeImage(it)
                        }
                    )

                    AnimatedVisibility(
                        visible = reviewState.images.size < 3,
                        modifier = Modifier.fillMaxWidth(),
                        enter = expandIn(),
                        exit = shrinkOut()
                    ) {
                        DottedBorderRectangle(
                            onBoxPressed = {
                                screenModel.setPickerState(true)
                            }
                        ) {
                            Image(
                                modifier = Modifier.fillMaxWidth().align(Alignment.Center),
                                painter = painterResource(Res.drawable.ic_take_picture),
                                contentDescription = null
                            )
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(
                        onClick = {
                            screenModel.submitReview(historyId)
                        },
                        enabled = reviewState.stars != 0 && reviewState.comment.isNotEmpty(),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Primary500)
                    ) {
                        Text(
                            text = "Submit Review",
                            style = MaterialTheme.typography.bodyMedium.copy(color = White)
                        )
                    }
                }
            }
        }

        CMPImagePickNCropDialog(
            imageCropper = rememberImageCropper(),
            openImagePicker = reviewState.openImagePicker,
            imagePickerDialogHandler = {
                screenModel.setPickerState(it)
            },
            selectedImageCallback = {
                screenModel.addImage(it)
            })

        LaunchedEffect(key1 = uiState.isSuccess) {
            if (uiState.isSuccess) { navigator.pop() }
        }
    }
}