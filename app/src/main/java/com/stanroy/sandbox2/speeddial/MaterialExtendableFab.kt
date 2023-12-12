package com.stanroy.sandbox2.speeddial

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MaterialExtendableFab(
    modifier: Modifier = Modifier,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable () -> Unit,
    extended: Boolean = true,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    contentColor: Color = contentColorFor(backgroundColor),
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier
            .sizeIn(
                minWidth = ExtendedFabSize,
                minHeight = ExtendedFabSize
            ),
        onClick = onClick,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
    ) {
        val endPadding = if (!extended) 0.dp else ExtendedFabIconPadding
        Row(
            modifier = Modifier.padding(
                end = endPadding
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AnimatedVisibility(visible = !extended) {
                icon()
            }

            AnimatedVisibility(visible = extended) {
                Row(verticalAlignment = Alignment.CenterVertically) {

                    Spacer(Modifier.width(ExtendedFabIconPadding))
                    text?.let {
                        it()
                    }
                }
            }
        }
    }
}

val EasingLinearCubicBezier = CubicBezierEasing(0.0f, 0.0f, 1.0f, 1.0f)
val EasingEmphasizedCubicBezier = CubicBezierEasing(0.2f, 0.0f, 0.0f, 1.0f)
private val ExtendedFabSize = 48.dp
private val ExtendedFabIconPadding = 12.dp
val xd = fadeOut()
private val ExtendedFabCollapseAnimation = fadeOut(
    animationSpec = tween(
        durationMillis = 100,
        easing = EasingLinearCubicBezier,
    )
) + shrinkHorizontally(
    animationSpec = tween(
        durationMillis = 100,
        easing = EasingEmphasizedCubicBezier,
    ),
    shrinkTowards = Alignment.Start,
)

private val ExtendedFabExpandAnimation = fadeIn(
    animationSpec = tween(
        durationMillis = 200,
        delayMillis = 100,
        easing = EasingLinearCubicBezier,
    ),
) + expandHorizontally(
    animationSpec = tween(
        durationMillis = 100,
        easing = EasingEmphasizedCubicBezier,
    ),
    expandFrom = Alignment.Start,
)

