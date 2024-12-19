package org.jj.notes.domain

enum class NotesPriority(val value:Int){
    Low(1),
    Medium(2),
    High(3),
}

data class Notes(
    val id:Int?=null,
    val title:String,
    val description: String,
    val priority: NotesPriority = NotesPriority.Low,
)
