package org.jj.notes.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Upsert
    suspend fun upsertNote(note: NotesEntity)

    @Query("SELECT * FROM NotesEntity")
     fun getNotes(): Flow<List<NotesEntity>>

    @Query("DELETE FROM NotesEntity where id = :id")
    suspend fun deleteNote(id:Int)
}