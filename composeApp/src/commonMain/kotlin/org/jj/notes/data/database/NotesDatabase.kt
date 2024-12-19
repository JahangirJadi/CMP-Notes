package org.jj.notes.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        NotesEntity::class,
    ],
    version = 1
)
@ConstructedBy(NoteDatabaseConstructor::class)
abstract class NotesDatabase: RoomDatabase() {

    abstract val notesDao: NotesDao

    companion object{
        const val DB_NAME = "note.db"
    }
}