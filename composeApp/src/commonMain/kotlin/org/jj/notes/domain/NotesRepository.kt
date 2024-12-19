package org.jj.notes.domain

import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    suspend fun upsertNote(notes: Notes)

    suspend fun deleteNote(id:Int)

    fun getNotes(): Flow<List<Notes>>

}