package de.paschelino.tell.parts

data class Host(val token: String) : HierPart {
    companion object {
        val EMPTY = Host("")
    }

    operator fun plus(port: Port) : Authority {
        return Authority(this, port)
    }

    operator fun plus(path: Path) : HierPart {
        return AbsoluteHierPart(Authority(this), path)
    }

    override fun toString() = token
}