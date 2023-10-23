package com.mzaragozaserrano.compose.composables.texts

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mzaragozaserrano.compose.utils.FontSize
import com.mzaragozaserrano.compose.utils.TextType
import com.mzaragozaserrano.presentation.R

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Normal.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Normal.size,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )
}

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    @StringRes textId: Int,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Normal.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Normal.size,
        maxLines = maxLines,
        text = stringResource(id = textId),
        textAlign = textAlign,
        textType = textType
    )
}

@Composable
fun NormalText(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    maxLines: Int = 1,
    text: AnnotatedString,
    textAlign: TextAlign = TextAlign.Start,
    textType: TextType = TextType.Adjust(FontSize.Normal.size),
) {
    BaseText(
        modifier = modifier,
        color = color,
        fontSize = FontSize.Normal.size,
        maxLines = maxLines,
        text = text,
        textAlign = textAlign,
        textType = textType
    )
}

@Preview(showBackground = true)
@Composable
private fun NormalTextPrev() {
    NormalText(text = stringResource(id = R.string.hello_world))
}