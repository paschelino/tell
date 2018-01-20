package de.paschelino.tell.parts

import de.paschelino.tell.Uri

data class Schema(val token: String) {
    init {
        val start = token.first().toString()
        if(!CharacterClasses.ALPHA.matches(start)) {
            throw MalformedException("Schema may not start with '$start'!")
        }
    }

    operator fun plus(hierPart: HierPart): Uri {
        return when (hierPart) {
            is AbsoluteHierPart -> Uri(this, hierPart)
            is Host -> Uri(this, AbsoluteHierPart(Authority(hierPart)))
            is Path -> Uri(this, hierPart)
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    override fun toString() = token.toLowerCase()
}