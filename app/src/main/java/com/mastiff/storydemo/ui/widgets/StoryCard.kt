package com.mastiff.storydemo.ui.widgets

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mastiff.storydemo.classes.Image
import kotlinx.coroutines.launch
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun StoryCard(modifier: Modifier = Modifier, images: Array<Image>) {
    val currentPage = remember { mutableStateOf(0) }
    val currentImage =  remember { derivedStateOf { images[if(currentPage.value < images.size) currentPage.value else currentPage.value - 1] } }

    val previousPage = remember { mutableStateOf(0) }

    val animationPlay = remember { mutableStateOf(true) }

    val interactionSource = remember { MutableInteractionSource() }

    val indicatorProgressAnimationValue = remember { Animatable(0f) }

    val coroutine = rememberCoroutineScope()

    LaunchedEffect(currentPage.value, animationPlay.value) {
        launch {
            indicatorProgressAnimationValue.animateTo(
                targetValue = 1f,
                animationSpec = tween(5000, easing = CubicBezierEasing(0f, 0.25f, 1f, 0.75f))
            )
            if (!animationPlay.value){
                indicatorProgressAnimationValue.snapTo(0f)
            }
            if (currentPage.value < images.size) {
                previousPage.value = currentPage.value
                currentPage.value += 1
            }
        }
    }

    Box(
        modifier = modifier
            .clip(AbsoluteSmoothCornerShape(18.dp, 60, 18.dp, 60, 18.dp, 60, 18.dp, 60))
            .background(Color.LightGray)
    )
    {
        AnimatedFloatingText(currentImage, currentPage, previousPage, images.size)

        images.forEachIndexed { index, image ->
            AnimatedImagePageItem(
                image.url, currentPage, previousPage, index, images.size
            )
        }
        ReplayPageItem(
            currentPage, previousPage, images.size
        )

        // 触控事件处理者
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = currentPage.value != 0,
                        onClick = {
                            coroutine.launch {
                                indicatorProgressAnimationValue.snapTo(0f)
                            }
                            previousPage.value = currentPage.value
                            currentPage.value -= 1
                        }
                    )
                    .zIndex(1000f)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(2f)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        onClick = {

                        }
                    )
                    .pointerInput(Unit){
                        detectTapGestures(
                            onTap = {
                                coroutine.launch {
                                    indicatorProgressAnimationValue.snapTo(0f)
                                }
                                previousPage.value = currentPage.value
                                if (currentPage.value != images.size) {
                                    currentPage.value += 1
                                } else {
                                    currentPage.value = 0
                                }
                            },
                            onLongPress = {

                            }
                        )
                    }
                    .zIndex(1000f)
            )
        }

        // 指示器
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .zIndex(1000f)
                .padding(top = 10.dp, start = 10.dp, end = 10.dp)
        ) {
            images.forEachIndexed { index, _ ->
                Box(
                    Modifier
                        .weight(1f)
                        .padding(horizontal = 2.dp)
                        .height(2.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(Color(0x68f3f3f3))
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth(
                                if (currentPage.value > index) 1f
                                else if (currentPage.value < index) 0f
                                else indicatorProgressAnimationValue.value
                            )
                            .height(4.dp)
                            .background(Color(0x88f3f3f3))
                    )
                }
            }
        }
    }
}