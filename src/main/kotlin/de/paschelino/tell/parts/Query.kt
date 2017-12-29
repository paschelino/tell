package de.paschelino.tell.parts

class Query(vararg val params: Param) {
    companion object {
        val EMPTY = Query()
    }

    operator fun plus(param: Param) : Query {
        return Query(*this.params, param)
    }

    override fun toString(): String {
        val queryBegin = if(params.isEmpty()) "" else "?"
        return queryBegin + params.joinToString(separator = "&") { it.key + "=" + it.value }
    }
}
