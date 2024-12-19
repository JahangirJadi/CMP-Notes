package org.jj.notes.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.jj.core.presentation.LightYellow


@Composable
fun NoteBackground(
    modifier: Modifier = Modifier,
    lineColor: Color = Color(0xFFD3D3D3),
    backgroundColor: Color = LightYellow, // Light yellow background
    lineSpacing: Dp = 24.dp,
    cornerRadius: Dp = 16.dp
) {
    androidx.compose.foundation.Canvas(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor, shape = RoundedCornerShape(cornerRadius))
    ) {
        val numberOfLines = (size.height / lineSpacing.toPx()).toInt()
        for (i in 1..numberOfLines) {
            val y = i * lineSpacing.toPx()
            drawLine(
                color = lineColor,
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = 1.dp.toPx()
            )
        }
    }
}