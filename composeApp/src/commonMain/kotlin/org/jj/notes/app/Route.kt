package org.jj.notes.app

import kotlinx.serialization.Serializable


sealed interface Route {

    @Serializable
    data object NotesGraph: Route

    @Serializable
    data object NoteList: Route
}