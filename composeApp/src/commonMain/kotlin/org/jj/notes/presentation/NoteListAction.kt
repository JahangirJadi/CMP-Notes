package org.jj.notes.presentation

import org.jj.notes.domain.Notes

sealed interface NoteListAction {

    data class OnAddNoteToDb(val notes: Notes): NoteListAction

    data class OnDeleteNote(val id:Int): NoteListAction

    data class OnCreateNote(val note:Notes?): NoteListAction

    data object OnDismissBottomSheet: NoteListAction
}