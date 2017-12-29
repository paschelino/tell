package de.paschelino.tell

import de.paschelino.tell.parts.Fragment
import de.paschelino.tell.parts.Schema
import de.paschelino.tell.parts.HierPart
import de.paschelino.tell.parts.Query

data class Uri(val schema: Schema, val hierPart: HierPart, val query: Query, val fragment: Fragment) {
    constructor(schema: Schema, hierPart: HierPart) : this(schema, hierPart, Query.EMPTY, Fragment.EMPTY)
    constructor(schema: Schema, hierPart: HierPart, query: Query) : this(schema, hierPart, query, Fragment.EMPTY)

    operator fun plus(query: Query): Uri = Uri(this.schema, this.hierPart, query)
    operator fun plus(fragment: Fragment): Uri = Uri(this.schema, this.hierPart, this.query, fragment)

    override fun toString(): String {
        return s(schema) + ":" + s(hierPart) + s(query) + s(fragment)
    }

    private fun s(part: Any) = part.toString()
}