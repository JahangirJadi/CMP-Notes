package org.jj.notes.data.database

import androidx.room.RoomDatabaseConstructor


@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object NoteDatabaseConstructor: RoomDatabaseConstructor<NotesDatabase> {

    override fun initialize(): NotesDatabase
}