package de.paschelino.tell.parts

import de.paschelino.tell.parts.Path.Companion.path

data class Segment private constructor(val token: String) {
    companion object {
        fun segment(rawToken: String): Segment {
            return Segment(extractToken(rawToken))
        }

        private fun extractToken(rawToken: String): String {
            val matchResult = format.matchEntire(rawToken)
            val unsafeToken : String? = matchResult?.groups?.get("token")?.value
            return unsafeToken ?: ""
        }

        val EMPTY = Segment("")
        private val format = """/*(?<token>[^/]*)/*""".toRegex()
    }

    operator fun plus(segment: Segment) : Path = path(this, segment)

    override fun toString(): String {
        return if(token.isEmpty()) "" else "/" + token
    }
}