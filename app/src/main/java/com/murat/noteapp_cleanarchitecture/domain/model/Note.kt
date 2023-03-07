package com.murat.noteapp_cleanarchitecture.domain.model

data class Note(
    val id: Int = DEFAULT_NOTE_ID,
    var title: String,
    var description: String,

    ): java.io.Serializable{
    companion object {
        const val DEFAULT_NOTE_ID = 0
    }
}
