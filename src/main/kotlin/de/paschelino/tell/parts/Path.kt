package de.paschelino.tell.parts

import de.paschelino.tell.parts.Segment.Companion.segment

data class Path(val segments: List<Segment>) : HierPart {

    companion object {
        val EMPTY = Path(emptyList())

        fun path(rawPath: String) : Path {
            val tokens = rawPath.split("""\/""".toRegex()).filter { !it.isEmpty() }
            return Path(tokens.map { segment(it) })
        }

        fun path(vararg segments: Segment) : Path {
            return Path(segments.asList())
        }
    }

    operator fun plus(path: Path): Path {
        return Path(segments + path.segments)
    }

    operator fun plus(segment: Segment): Path {
        return Path(segments + listOf(segment))
    }

    override fun toString(): String {
        return segments.joinToString(separator = "")
    }
}