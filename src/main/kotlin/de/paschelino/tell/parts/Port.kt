package de.paschelino.tell.parts

data class Port(val portNumber: Int) {
    companion object {
        val DEFAULT = Port(80)
        val EMPTY = Port(-1)
    }

    override fun toString() = portNumber.toString()
}


