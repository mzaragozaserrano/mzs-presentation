package com.mzs.core.presentation.components.compose.backgrounds

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedEdgeBackground(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    borderColor: Color,
    cornerRadius: Dp,
    strokeBorder: Dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        shape = RoundedCornerShape(size = cornerRadius),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        border = BorderStroke(color = borderColor, width = strokeBorder),
        modifier = modifier,
        content = content
    )
}

@Preview
@Composable
private fun RoundedEdgeBackgroundPrev() {
    RoundedEdgeBackground(
        modifier = Modifier.padding(all = 16.dp),
        backgroundColor = Color.White,
        borderColor = Color.Black,
        cornerRadius = 12.dp,
        strokeBorder = 1.dp
    ) {
        Text(
            modifier = Modifier.padding(all = 16.dp),
            color = Color.Black,
            text = "This is a trial text"
        )
    }
}