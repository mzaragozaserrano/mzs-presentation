package com.mzs.core.presentation.components.compose.buttons

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PushedButton(
    modifier: Modifier = Modifier,
    buttonBackgroundColor: Color,
    text: String,
    textColor: Color,
    textStyle: TextStyle,
    onButtonClicked: () -> Unit,
) {

    var isPressed by remember { mutableStateOf(value = false) }
    var elementSize by remember { mutableStateOf(IntSize.Zero) }
    var isClickAvailable by remember { mutableStateOf(value = true) }
    val scale = animateFloatAsState(targetValue = if (isPressed) 0.93f else 1f, label = "")

    Button(
        modifier = modifier
            .scale(scale = scale.value)
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isPressed = true
                    }

                    MotionEvent.ACTION_MOVE -> {
                        if(isPressed) {
                            val x = motionEvent.x.toInt()
                            val y = motionEvent.y.toInt()
                            isPressed =
                                x in 0 until elementSize.width && y in 0 until elementSize.height
                            isClickAvailable = false
                        }
                    }

                    MotionEvent.ACTION_UP -> {
                        if (isClickAvailable) {
                            isPressed = false
                            onButtonClicked()
                        } else {
                            isClickAvailable = true
                        }
                    }
                }
                true
            }
            .onGloballyPositioned { layoutCoordinates ->
                elementSize = layoutCoordinates.size
            },
        colors = ButtonDefaults.buttonColors(containerColor = buttonBackgroundColor),
        shape = RoundedCornerShape(size = 8.dp),
        onClick = {}
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            color = textColor,
            style = textStyle,
            text = text.uppercase(),
            textAlign = TextAlign.Center
        )
    }

}

@Preview(apiLevel = 34)
@Composable
private fun PushedButtonPrev() {
    PushedButton(
        buttonBackgroundColor = Color.LightGray,
        textColor = Color.Black,
        text = "Accept",
        textStyle = MaterialTheme.typography.titleSmall
    ) {
        //Here will go the action when clicking on the button
    }
}