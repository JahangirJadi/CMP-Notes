package org.jj.notes.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jj.core.presentation.LightYellow
import org.jj.core.presentation.Yellow
import org.jj.core.presentation.components.NoteActionButton
import org.jj.core.presentation.components.NoteOutlinedTextField
import org.jj.notes.domain.Notes

@Composable
fun NoteBottomSheet(
    modifier: Modifier = Modifier,
    isVisible:Boolean = false,
    selectedNote: Notes? = null,
    onUpsertNote: (Notes) -> Unit,
    onDismiss: () -> Unit,
) {

    AnimatedVisibility(visible = isVisible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
        modifier = modifier){


        var title by remember(selectedNote?.title) {
            mutableStateOf(selectedNote?.title?:"")
        }

        var desc by remember(selectedNote?.description) {
            mutableStateOf(selectedNote?.description?:"")
        }

        Column(modifier = Modifier
            .background(color = LightYellow).fillMaxWidth()) {

            Box(
                modifier = Modifier
                    .background(color = Yellow)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Create a Note",
                    color = Color.Black)
                IconButton(
                    modifier = Modifier.align(Alignment.TopEnd),
                    onClick = onDismiss
                ) {
                    Icon(

                        imageVector = Icons.Default.Close,
                        contentDescription = null,
                        tint = Color.Black
                    )
                }
            }


            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                NoteOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChanged = {
                        title = it
                    },
                    hint = "Note title",
                    singleLine = true
                )


                NoteOutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = desc,
                    onValueChanged = {
                        desc = it
                    },
                    hint = "Note description"
                )

                NoteActionButton(modifier = Modifier.fillMaxWidth(),
                    title = if (selectedNote == null) "Save" else "Update",
                    isEnabled = title != "" && desc != "",
                    onClick = {
                        val note = Notes(
                            id = selectedNote?.id,
                            title = title,
                            description = desc,
                        )
                        onUpsertNote(note)

                    })

            }

        }
    }


}