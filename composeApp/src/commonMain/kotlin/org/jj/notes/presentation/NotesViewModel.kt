package org.jj.notes.presentation

import androidx.compose.material.BottomSheetValue
import androidx.compose.material.rememberBottomSheetState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jj.notes.domain.NotesRepository

class NotesViewModel(
    private val notesRepository: NotesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NoteListState())
    val state = _state
        .onStart {
            observeNotes()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000L),
            _state.value
        )

    private fun observeNotes() {

        notesRepository.getNotes()
            .onEach { notes ->
                _state.update {
                    it.copy(
                        noteList = notes,
                        isLoading = false
                    )
                }

            }
            .launchIn(viewModelScope)

    }


    fun onAction(action: NoteListAction) {
        when (action) {
            is NoteListAction.OnAddNoteToDb -> {
                viewModelScope.launch {
                    notesRepository.upsertNote(action.notes)
                }
                _state.update {
                    it.copy(
                        isBottomSheetOpen = false,
                        selectedNote = null
                    )
                }
            }

            NoteListAction.OnDismissBottomSheet -> {
                _state.update {
                    it.copy(
                        isBottomSheetOpen = false,
                        selectedNote = null
                    )
                }
            }

            is NoteListAction.OnCreateNote -> {
                _state.update {
                    it.copy(
                        isBottomSheetOpen = true,
                        selectedNote = action.note
                    )
                }
            }

            is NoteListAction.OnDeleteNote -> {
                viewModelScope.launch {
                    notesRepository.deleteNote(action.id)
                }
            }
        }
    }
}