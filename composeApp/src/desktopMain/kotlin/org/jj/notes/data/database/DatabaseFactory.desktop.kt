package org.jj.notes.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<NotesDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "Notes")
            os.contains("mac") -> File(userHome, "Library/Application Support/Notes")
            else -> File(userHome, ".local/share/Notes")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, NotesDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}