package de.paschelino.tell.parts

data class UserInfo(val token: String) {
    companion object {
        val EMPTY = UserInfo("")
    }

    operator fun plus(host: Host) : Authority {
        return Authority(userInfo = this, host = host)
    }

    override fun toString() = token
}