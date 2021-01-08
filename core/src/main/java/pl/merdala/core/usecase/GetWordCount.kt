package pl.merdala.core.usecase

import pl.merdala.core.data.Note

class GetWordCount {

    operator fun invoke(note: Note) = getCount(note.content) + getCount(note.title)

    private fun getCount(str: String) =
        str.split(",", ";", ":", "\n", " ", "\t")
            .filter { it.contains(Regex(".*\\w.*")) }
            .count()

}