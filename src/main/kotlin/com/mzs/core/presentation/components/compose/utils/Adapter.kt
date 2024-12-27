package com.mzs.core.presentation.components.compose.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mzs.core.presentation.utils.generic.ItemOrientation

@Composable
fun <T> Adapter(
    modifier: Modifier = Modifier,
    arrangement: Arrangement.HorizontalOrVertical? = null,
    colorDivider: Color? = null,
    contentPadding: Dp,
    isScrollable: Boolean,
    itemOrientation: ItemOrientation,
    lineModifier: Modifier = Modifier.padding(
        horizontal = if (itemOrientation is ItemOrientation.Vertical) contentPadding else 0.dp,
        vertical = if (itemOrientation is ItemOrientation.Horizontal) contentPadding else 0.dp,
    ),
    items: List<T>,
    item: @Composable (Int, T) -> Unit,
) {

    when (itemOrientation) {
        is ItemOrientation.Horizontal -> {
            HorizontalAdapter(
                modifier = modifier,
                arrangement = arrangement,
                colorDivider = colorDivider,
                contentPadding = PaddingValues(horizontal = contentPadding),
                isScrollable = isScrollable,
                lineModifier = lineModifier,
                items = items,
                item = item
            )
        }

        is ItemOrientation.Vertical -> {
            VerticalAdapter(
                modifier = modifier,
                arrangement = arrangement,
                colorDivider = colorDivider,
                contentPadding = PaddingValues(vertical = contentPadding),
                isScrollable = isScrollable,
                lineModifier = lineModifier,
                items = items,
                item = item
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun <T> HorizontalAdapter(
    modifier: Modifier,
    arrangement: Arrangement.HorizontalOrVertical?,
    colorDivider: Color?,
    contentPadding: PaddingValues,
    isScrollable: Boolean,
    lineModifier: Modifier,
    items: List<T>,
    item: @Composable (Int, T) -> Unit,
) {
    if (isScrollable) {
        CompositionLocalProvider(value = LocalOverscrollConfiguration provides null) {
            LazyRow(
                modifier = modifier,
                contentPadding = contentPadding,
                horizontalArrangement = arrangement ?: Arrangement.Start,
                content = {
                    itemsIndexed(items = items) { index, item ->
                        item(index, item)
                        if (colorDivider != null && index < items.size - 1) {
                            Line(
                                modifier = lineModifier,
                                color = colorDivider,
                                itemOrientation = ItemOrientation.Horizontal
                            )
                        }
                    }
                }
            )
        }
    } else {
        Row(modifier = modifier, horizontalArrangement = arrangement ?: Arrangement.Start) {
            items.forEachIndexed { index, item ->
                item(index, item)
                if (colorDivider != null && index < items.size - 1) {
                    Line(
                        modifier = lineModifier,
                        color = colorDivider,
                        itemOrientation = ItemOrientation.Horizontal
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun <T> VerticalAdapter(
    modifier: Modifier,
    arrangement: Arrangement.HorizontalOrVertical?,
    colorDivider: Color?,
    contentPadding: PaddingValues,
    isScrollable: Boolean,
    lineModifier: Modifier,
    items: List<T>,
    item: @Composable (Int, T) -> Unit,
) {
    if (isScrollable) {
        CompositionLocalProvider(value = LocalOverscrollConfiguration provides null) {
            LazyColumn(
                modifier = modifier,
                contentPadding = contentPadding,
                verticalArrangement = arrangement ?: Arrangement.Top,
                content = {
                    itemsIndexed(items = items) { index, item ->
                        item(index, item)
                        if (colorDivider != null && index < items.size - 1) {
                            Line(
                                modifier = lineModifier,
                                color = colorDivider,
                                itemOrientation = ItemOrientation.Vertical
                            )
                        }
                    }
                }
            )
        }
    } else {
        Column(modifier = modifier, verticalArrangement = arrangement ?: Arrangement.Top) {
            items.forEachIndexed { index, item ->
                item(index, item)
                if (colorDivider != null && index < items.size - 1) {
                    Line(
                        modifier = lineModifier,
                        color = colorDivider,
                        itemOrientation = ItemOrientation.Vertical
                    )
                }
            }
        }
    }
}