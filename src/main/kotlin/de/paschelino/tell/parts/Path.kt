package de.paschelino.tell.parts

import de.paschelino.tell.parts.Segment.Companion.segment
import java.nio.file.Paths

data class Path(val segments: List<Segment>) : HierPart {

    companion object {
        val EMPTY = Path(emptyList())

        fun path(rawPath: String) : Path {
            return Path(rawPath.split("""\/""".toRegex()).map { segment(it) })
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