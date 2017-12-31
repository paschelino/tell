package de.paschelino.tell.parts

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

    override fun toString(): String {
        return "/" + token
    }
}