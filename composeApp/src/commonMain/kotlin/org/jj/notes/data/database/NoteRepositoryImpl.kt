package org.jj.notes.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jj.notes.data.mapper.ToNoteEntity
import org.jj.notes.data.mapper.ToNotes
import org.jj.notes.domain.Notes
import org.jj.notes.domain.NotesRepository

class NoteRepositoryImpl(
    private val notesDao: NotesDao
): NotesRepository {
    override suspend fun upsertNote(notes: Notes) {
        notesDao.upsertNote(notes.ToNoteEntity())
    }

    override suspend fun deleteNote(id: Int) {
        notesDao.deleteNote(id)
    }

    override  fun getNotes(): Flow<List<Notes>> {
        return notesDao.getNotes().map { noteEntity ->
            noteEntity.map {
                it.ToNotes()
            }
        }
    }

}