package de.paschelino.tell.parts

class Query(val params: List<Param>) {
    companion object {
        val EMPTY = Query(emptyList())

        fun query(vararg params: Param) = Query(params.asList())
    }

    operator fun plus(param: Param) : Query {
        return Query(this.params + param)
    }

    override fun toString(): String {
        val queryBegin = if(params.isEmpty()) "" else "?"
        return queryBegin + params.joinToString(separator = "&") { it.key + "=" + it.value }
    }
}
