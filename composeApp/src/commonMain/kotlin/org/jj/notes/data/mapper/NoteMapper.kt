package org.jj.notes.data.mapper

import org.jj.notes.data.database.NotesEntity
import org.jj.notes.domain.Notes
import org.jj.notes.domain.NotesPriority

fun Notes.ToNoteEntity(): NotesEntity{
    return NotesEntity(
        id = id?:0,
        title=title,
        priority = priority.value,
        description = description
    )
}


fun NotesEntity.ToNotes(): Notes{
    return Notes(
        id = id,
        title=title,
        priority = NotesPriority.entries[priority - 1],
        description = description
    )
}