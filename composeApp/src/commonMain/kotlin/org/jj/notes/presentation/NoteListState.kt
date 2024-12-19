package org.jj.notes.presentation

import androidx.compose.runtime.Stable
import org.jj.notes.domain.Notes

@Stable
data class NoteListState(
    val selectedNote: Notes?=null,
    val noteList: List<Notes> = emptyList(),
    val isLoading: Boolean = true,
    val isBottomSheetOpen: Boolean = false

)
