package de.paschelino.tell.parts

data class AbsoluteHierPart(val authority: Authority, val path: Path) : HierPart {
    constructor(authority: Authority) : this(authority, Path.EMPTY)

    override fun toString() = "//" + authority.toString() + path.toString()
}