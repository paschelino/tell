package de.paschelino.tell.parts

data class Path(val token: String) : HierPart {
    companion object {
        val EMPTY = Path("")
    }

    operator fun plus(path: Path): Path {
        return Path(token + path.toString())
    }

    override fun toString(): String {
        val pathBegin = if(token.isEmpty()) "" else "/"
        return pathBegin + token
    }
}