package com.mastiff.storydemo.ui.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AnimatedImagePageItem(
    url: String,
    currentPage: MutableState<Int>,
    previousPage: MutableState<Int>,
    index: Int,
    maxCount: Int
) {
        val imageBlurAnimationValue = remember { Animatable(0f) }
        val imageScaleAnimationValue = remember { Animatable(1.2f) }
        val imageAlphaAnimationValue = remember { Animatable(1f) }
        val coroutineScope = rememberCoroutineScope()


        fun animateValue(
            coroutineScope: CoroutineScope,
            animatable: Animatable<Float, *>,
            isForward: Boolean,
            startValue: Float,
            endValue: Float
        ) {
            coroutineScope.launch {
                animatable.snapTo(startValue)
                if (isForward){
                    animatable.animateTo(endValue, animationSpec = tween(800, easing = CubicBezierEasing(0.25f, 0f, 0f, 1f)))
                }
                else{
                    animatable.animateTo(endValue, animationSpec = tween(600, easing = CubicBezierEasing(0f, 1f, 0f, 1f)))
                }
            }
        }


        LaunchedEffect(currentPage.value) {
            val isCurrentPage = currentPage.value == index
            val isForward = currentPage.value > previousPage.value

            val (blurStart, blurEnd) = when {
                isForward && isCurrentPage -> 10f to 0f
                isForward -> 0f to 50f
                isCurrentPage -> 50f to 0f
                else -> 0f to 10f
            }

            val (scaleStart, scaleEnd) = when {
                isForward && isCurrentPage -> 1f to 1.2f
                isForward -> 1.2f to 3f
                isCurrentPage -> 3f to 1.2f
                else -> 1.2f to 1f
            }

            val (alphaStart, alphaEnd) = when {
                isForward && isCurrentPage -> 0f to 1f
                isForward -> 1f to 0f
                isCurrentPage -> 0f to 1f
                else -> 1f to 0f
            }

            if (index != maxCount) {
                animateValue(
                    coroutineScope,
                    imageBlurAnimationValue,
                    isForward,
                    blurStart,
                    blurEnd)
                animateValue(
                    coroutineScope,
                    imageScaleAnimationValue,
                    isForward,
                    scaleStart,
                    scaleEnd
                )
                animateValue(
                    coroutineScope,
                    imageAlphaAnimationValue,
                    isForward,
                    alphaStart,
                    alphaEnd
                )
            }
        }

    if (index == currentPage.value || index == previousPage.value) {
        AsyncImage(
            model = url,
            modifier = Modifier
                .fillMaxSize()
                .blur(imageBlurAnimationValue.value.dp)
                .scale(imageScaleAnimationValue.value)
                .alpha(imageAlphaAnimationValue.value)
            ,
            contentDescription = "",
            contentScale = ContentScale.FillHeight
        )
    }
}