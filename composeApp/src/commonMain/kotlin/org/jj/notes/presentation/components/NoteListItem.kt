
package org.jj.notes.presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cmp_notes_app.composeapp.generated.resources.Res
import cmp_notes_app.composeapp.generated.resources.notes
import org.jetbrains.compose.resources.painterResource
import org.jj.core.presentation.Yellow
import org.jj.notes.domain.Notes

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListItem(
    modifier: Modifier = Modifier,
    note: Notes,
    onClick: () -> Unit,
    onDeleteNote: () -> Unit,
    backgroundColor: Color = Yellow,
    cornerRadius: Dp = 16.dp,
    foldColor: Color = Color.Black

) {

    Box(
        modifier = modifier
            .combinedClickable(
                onLongClick = onDeleteNote,
                onClick = onClick,
            )
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

//        NoteBackgroundCanvas(modifier = Modifier.fillMaxSize())

        Row(
            modifier = Modifier
                .drawBehind {

                    drawRoundRect(
                        color = backgroundColor,
                        size = Size(size.width, size.height),
                        cornerRadius = androidx.compose.ui.geometry.CornerRadius(cornerRadius.toPx())
                    )

                    // Draw the folded corner
                    val foldSize = 40.dp.toPx()
                    val path = Path().apply {
                        moveTo(size.width, size.height - foldSize)
                        lineTo(size.width - foldSize, size.height)
                        lineTo(size.width, size.height)
                        close()
                    }
                    drawPath(
                        path = path,
                        color = foldColor,
                        style = Fill
                    )

                }.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                painter = painterResource(Res.drawable.notes),
                contentDescription = null,
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    note.title,
                    style = MaterialTheme.typography.h6
                )

                Text(
                    note.description,
                )
            }

        }


    }

}