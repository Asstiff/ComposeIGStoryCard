package com.mastiff.storydemo.ui.widgets

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import com.mastiff.storydemo.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ReplayPageItem(
    currentPage: MutableState<Int>,
    previousPage: MutableState<Int>,
    maxCount: Int
) {
    val imageBlurAnimationValue = remember { Animatable(0f) }
    val imageScaleAnimationValue = remember { Animatable(1.2f) }
    val imageAlphaAnimationValue = remember { Animatable(1f) }
    val coroutineScope = rememberCoroutineScope()


    fun animateValue(
        coroutineScope: CoroutineScope,
        animatable: Animatable<Float, *>,
        startValue: Float,
        endValue: Float
    ) {
        coroutineScope.launch {
            animatable.snapTo(startValue)
            animatable.animateTo(
                endValue,
                animationSpec = tween(800, easing = CubicBezierEasing(0.25f, 0f, 0f, 1f))
            )
        }
    }


    LaunchedEffect(currentPage.value == maxCount) {
        val isCurrentPage = currentPage.value == maxCount

        val (blurStart, blurEnd) = when {
            isCurrentPage -> 10f to 0f
            else -> 0f to 10f
        }

        val (scaleStart, scaleEnd) = when {
            isCurrentPage -> 1f to 1.2f
            else -> 1.2f to 1f
        }

        val (alphaStart, alphaEnd) = when {
            isCurrentPage -> 0f to 1f
            else -> 1f to 0f
        }

        animateValue(coroutineScope, imageBlurAnimationValue, blurStart, blurEnd)
        animateValue(
            coroutineScope,
            imageScaleAnimationValue,
            scaleStart,
            scaleEnd
        )
        animateValue(
            coroutineScope,
            imageAlphaAnimationValue,
            alphaStart,
            alphaEnd
        )
    }

    if (currentPage.value == maxCount || previousPage.value == maxCount) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(imageBlurAnimationValue.value.dp)
                .scale(imageScaleAnimationValue.value)
                .alpha(imageAlphaAnimationValue.value)
                .zIndex(maxCount.toFloat())
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush
                    .linearGradient(
                        listOf(Color(0xff80520a), Color(0xff8b0038))
                    )
                )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.round_replay_24),
                    contentDescription = "",
                )
                Text(
                    text = stringResource(id = R.string.REPLAY_BUTTON),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        shadow = Shadow(
                            color = Color(0x30000000),
                            blurRadius = 18.0f
                        )
                    )
                )
            }
        }

    }
}