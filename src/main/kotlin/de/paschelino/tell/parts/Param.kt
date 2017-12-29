package de.paschelino.tell.parts

data class Param(val key: String, val value: Comparable<*>) {
    operator fun plus(param: Param) : Query {
        return Query(this, param)
    }
}