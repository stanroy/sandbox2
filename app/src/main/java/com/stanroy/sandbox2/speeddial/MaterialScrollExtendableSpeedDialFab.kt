package com.stanroy.sandbox2.speeddial

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MaterialScrollExtendableSpeedDialFab(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    vararg speedDialOptions: SpeedDialOption,
    text: @Composable (() -> Unit)? = null,
    icon: @Composable () -> Unit,
    backgroundColor: Color = MaterialTheme.colors.secondary,
    contentColor: Color = contentColorFor(backgroundColor),
    onSpeedDialOpen: (Boolean) -> Unit
) {
    var speedDialOpen by remember { mutableStateOf(false) }
    var isFabExtended by remember { mutableStateOf(true) }
    var wasFabExtendedBeforeClick by remember { mutableStateOf(isFabExtended) }
    var currentScrollValue by remember { mutableStateOf(0) }
    var previousScrollValue by remember { mutableStateOf(0) }

    val threshold = 64.dp.value.toInt()
    LaunchedEffect(scrollState.value) {
        currentScrollValue = scrollState.value

        if (currentScrollValue > previousScrollValue + threshold) {
            // Scrolling down
            // Perform actions or update state when scrolling down
            Log.d("TAG_TAG", "Scrolling down")
            isFabExtended = false
        } else if (currentScrollValue < previousScrollValue - threshold) {
            // Scrolling up
            // Perform actions or update state when scrolling up
            Log.d("TAG_TAG", "Scrolling up")
            isFabExtended = true
        }

        wasFabExtendedBeforeClick = isFabExtended
        previousScrollValue = currentScrollValue
    }
//    Log.d("TAG_TAG", "currentScrollValue $currentScrollValue")
//    Log.d("TAG_TAG", "currentValue ${scrollState.value}")
//    Log.d("TAG_TAG", "maxValue ${scrollState.maxValue}")

    MaterialExtendableSpeedDialFab(
        modifier = modifier,
        speedDialOptions = speedDialOptions,
        extended = isFabExtended,
        icon = icon,
        text = text,
        speedDialOpen = speedDialOpen,
        contentColor = contentColor,
        onClick = {
            speedDialOpen = !speedDialOpen
            isFabExtended = when {
                speedDialOpen -> {
                    false
                }
                !speedDialOpen && !wasFabExtendedBeforeClick -> {
                    false
                }
                else -> {
                    true
                }
            }
        }
    )

    onSpeedDialOpen(speedDialOpen)
}