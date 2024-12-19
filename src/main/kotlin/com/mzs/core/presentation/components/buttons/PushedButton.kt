package com.mzs.core.presentation.components.buttons

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.components.backgrounds.RoundedBackground
import com.thecocktailapp.core.presentation.compose.components.texts.SmallMediumText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PushedButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    text: String,
    textColor: Color,
    textPaddingHorizontal: Dp = 24.dp,
    textPaddingVertical: Dp = 24.dp,
    onButtonClicked: () -> Unit,
) {

    var isPressed by remember { mutableStateOf(value = false) }
    val scale = animateFloatAsState(targetValue = if (isPressed) 0.93f else 1f, label = "")

    RoundedBackground(
        modifier = modifier
            .scale(scale = scale.value)
            .clip(shape = RoundedCornerShape(size = 8.dp))
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_CANCEL -> {
                        isPressed = false
                    }

                    MotionEvent.ACTION_DOWN -> {
                        isPressed = true
                    }

                    MotionEvent.ACTION_UP -> {
                        isPressed = false
                        onButtonClicked()
                    }
                }
                true
            },
        backgroundColor = buttonBackgroundColor,
        cornerRadius = 8.dp
    ) {
        SmallMediumText(
            modifier = Modifier
                .padding(horizontal = textPaddingHorizontal, vertical = textPaddingVertical)
                .align(alignment = Alignment.CenterHorizontally),
            color = textColor,
            text = text.uppercase(),
            textAlign = TextAlign.Center
        )
    }

}

@Preview
@Composable
private fun PushedButtonPrev() {
    PushedButton(
        buttonBackgroundColor = Color.LightGray,
        textColor = Color.Black,
        text = "Accept"
    ) {
        //Here will go the action when clicking on the button
    }
}