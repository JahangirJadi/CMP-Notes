package org.jj.notes.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cmp_notes_app.composeapp.generated.resources.Res
import cmp_notes_app.composeapp.generated.resources.create_note
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jj.notes.presentation.components.NoteBackground
import org.jj.notes.presentation.components.NoteBottomSheet
import org.jj.notes.presentation.components.NoteList
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NotesScreenRoot(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NotesScreen(
        modifier = modifier,
        state = state,
        onAction = viewModel::onAction
    )

}

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    onAction: (NoteListAction) -> Unit,
    state: NoteListState
) {

    val scope = rememberCoroutineScope()


    val bottomSheet = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    Scaffold(
        modifier = modifier,

        floatingActionButton = {

            AnimatedVisibility(!state.isBottomSheetOpen) {
                Image(
                    modifier = Modifier.clickable {
                        scope.launch {

                            bottomSheet.expand()
                            onAction(NoteListAction.OnCreateNote(null))

                        }
                    }.size(80.dp),
                    painter = painterResource(Res.drawable.create_note),
                    contentDescription = null,

                )
            }

        },

        ) {

        Box(modifier = Modifier.fillMaxSize()) {

            NoteBackground(
                modifier = Modifier.fillMaxSize()
            )


            NoteList(noteList = state.noteList,
                onClick = { note ->
                    onAction(NoteListAction.OnCreateNote(note))
                },
                onDeleteNote = {
                    onAction(NoteListAction.OnDeleteNote(it))
                })


                NoteBottomSheet(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    isVisible = state.isBottomSheetOpen,
                    selectedNote = state.selectedNote,
                    onUpsertNote = { note ->
                        onAction(NoteListAction.OnAddNoteToDb(note))
                        scope.launch {
                            bottomSheet.collapse()
                            onAction(NoteListAction.OnDismissBottomSheet)
                        }
                    },
                    onDismiss = {
                        scope.launch {
                            bottomSheet.collapse()
                            onAction(NoteListAction.OnDismissBottomSheet)

                        }
                    }

                )

        }


    }


}