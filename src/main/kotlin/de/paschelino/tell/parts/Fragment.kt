package de.paschelino.tell.parts

data class Fragment(val token: String) {
    companion object {
        val EMPTY = Fragment("")
    }

    override fun toString(): String {
        val fragmentBegin = if(token.isEmpty()) "" else "#"
        return fragmentBegin + token
    }
}