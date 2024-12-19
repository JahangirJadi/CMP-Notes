@file:OptIn(ExperimentalFoundationApi::class)

package org.jj.notes.presentation.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jj.notes.domain.Notes

@Composable
fun NoteList(
    modifier: Modifier = Modifier,
    noteList: List<Notes>,
    onClick:(Notes)->Unit,
    onDeleteNote:(Int)->Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(noteList) { note ->
            NoteListItem(
                modifier = Modifier.animateItem(
                     placementSpec = tween(),
                    fadeInSpec = tween()
                ),

                note = note,
                onDeleteNote = {
                    onDeleteNote(note.id!!)
                },
                onClick = {
                    onClick(note)
                }
            )
        }
    }
}