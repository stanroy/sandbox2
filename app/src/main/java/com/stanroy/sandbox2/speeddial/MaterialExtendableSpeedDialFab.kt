package com.stanroy.sandbox2.speeddial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class SpeedDialOption(
    val icon: Int,
    val iconTint: Color = Color.Black,
    val text: String,
    val onClick: () -> Unit
)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MaterialExtendableSpeedDialFab(
    modifier: Modifier = Modifier,
    vararg speedDialOptions: SpeedDialOption,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable () -> Unit,
    speedDialOpen: Boolean = false,
    extended: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    contentColor: Color = contentColorFor(backgroundColor),
    onClick: () -> Unit
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.End) {
        LazyColumn {
            itemsIndexed(speedDialOptions) { index, option ->
                val reversedIndex = speedDialOptions.size - 1 - index
                AnimatedVisibility(
                    visible = speedDialOpen,
                    enter = speedDialEnterAnimation(reversedIndex * 10),
                    exit = fadeOut()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        option.text.let { text ->
                            HorizontalSpacer(width = 8.dp)
                            Text(
                                modifier = Modifier
                                    .clip(shape = RoundedCornerShape(8.dp))
                                    .clickable(onClick = option.onClick)
                                    .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
                                    .padding(horizontal = 4.dp, vertical = 4.dp),
                                text = text,
                                color = Color.Black,
                                fontSize = 12.sp
                            )
                        }
                        HorizontalSpacer(width = 16.dp)
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .background(color = Color.Gray, shape = CircleShape)
                                .clip(CircleShape)
                                .clickable(onClick = option.onClick),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(modifier = Modifier.size(24.dp), painter = painterResource(id = option.icon), contentDescription = null, tint = option.iconTint)
                        }
                    }
                }
                if (index == speedDialOptions.lastIndex)
                    VerticalSpacer(height = 24.dp)
                else
                    VerticalSpacer(height = 12.dp)
            }
        }
        MaterialExtendableFab(
            text = text,
            icon = icon,
            extended = extended,
            backgroundColor = backgroundColor,
            contentColor = contentColor,
            onClick = onClick
        )
    }
}

fun speedDialEnterAnimation(delayMillis: Int = 0): EnterTransition =
    fadeIn(tween(delayMillis = delayMillis)) + expandIn(clip = false, expandFrom = Alignment.TopEnd)

@OptIn(ExperimentalAnimationApi::class)
fun speedDialExitAnimation(delayMillis: Int = 0): ExitTransition = scaleOut(tween(delayMillis = delayMillis))