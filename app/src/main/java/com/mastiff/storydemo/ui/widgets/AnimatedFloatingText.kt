package com.mastiff.storydemo.ui.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.mastiff.storydemo.classes.Image
import kotlinx.coroutines.launch
import racra.compose.smooth_corner_rect_library.AbsoluteSmoothCornerShape

@Composable
fun AnimatedFloatingText(
    currentImage: State<Image>,
    currentPage: MutableState<Int>,
    previousPage: MutableState<Int>,
    maxCount: Int,
) {
    val floatingTextTitleBlurValue = remember { Animatable(20f) }
    val floatingTextTitleScaleValue = remember { Animatable(0f) }
    val floatingTextTitleAlphaValue = remember { Animatable(1f) }


    LaunchedEffect(currentPage.value) {
        if (currentPage.value != maxCount && currentPage.value >= previousPage.value) {
            launch {
                floatingTextTitleBlurValue.snapTo(20f)
                floatingTextTitleBlurValue.animateTo(0f, animationSpec = tween(600))
            }
            launch {
                floatingTextTitleScaleValue.snapTo(0f)
                floatingTextTitleScaleValue.animateTo(
                    1f,
                    animationSpec = tween(900, easing = CubicBezierEasing(0f, 1.25f, 0.2f, 1f))
                )
            }
            launch {
                floatingTextTitleAlphaValue.snapTo(0f)
                floatingTextTitleAlphaValue.animateTo(
                    1f,
                    animationSpec = tween(200)
                )
            }
        } else if (currentPage.value != maxCount && currentPage.value < previousPage.value) {
            launch {
                floatingTextTitleBlurValue.snapTo(10f)
                floatingTextTitleBlurValue.animateTo(0f, animationSpec = tween(400))
            }
            launch {
                floatingTextTitleScaleValue.snapTo(2f)
                floatingTextTitleScaleValue.animateTo(
                    1f,
                    animationSpec = tween(800, easing = CubicBezierEasing(0f, 1f, 0.2f, 1f))
                )
            }
            launch {
                floatingTextTitleAlphaValue.snapTo(0f)
                floatingTextTitleAlphaValue.animateTo(
                    1f,
                    animationSpec = tween(300)
                )
            }
        } else {
            launch {
                floatingTextTitleBlurValue.animateTo(30f, animationSpec = tween(600))
            }
            launch {
                floatingTextTitleScaleValue.animateTo(
                    3f,
                    animationSpec = tween(800)
                )
            }
            launch {
                floatingTextTitleAlphaValue.animateTo(
                    0f,
                    animationSpec = tween(300)
                )
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxHeight()
            .zIndex(1000f)
            .blur(floatingTextTitleBlurValue.value.dp)
            .scale(floatingTextTitleScaleValue.value)
            .alpha(floatingTextTitleAlphaValue.value)
    ) {
        Column(
            modifier = Modifier
                .clip(
                    AbsoluteSmoothCornerShape(
                        18.dp, 60,
                        18.dp, 60,
                        0.dp, 60,
                        0.dp, 60,
                    )
                )
                .background(
                    Brush
                        .verticalGradient(
                            listOf(
                                Color(0xa8040404),
                                Color.Transparent,
                            )
                        )
                )
                .padding(bottom = 16.dp)
        ) {
            Text(
                text = currentImage.value.title,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(color = Color.Black, blurRadius = 24f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 6.dp, top = 24.dp)
            )
            Text(
                text = currentImage.value.description,
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    shadow = Shadow(color = Color.Black, blurRadius = 16f)
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 6.dp)
            )
        }
    }
}