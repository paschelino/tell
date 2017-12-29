package de.paschelino.tell.parts

data class Authority(val userInfo: UserInfo, val host: Host, val port: Port) {
    constructor(host: Host) : this(userInfo = UserInfo.EMPTY, host = host, port = Port.DEFAULT)
    constructor(host: Host, port: Port) : this(userInfo = UserInfo.EMPTY, host = host, port = port)
    constructor(userInfo: UserInfo, host: Host) : this(userInfo = userInfo, host = host, port = Port.DEFAULT)

    companion object {
        val EMPTY = Authority(UserInfo.EMPTY, Host.EMPTY, Port.EMPTY)
    }

    operator fun plus(path: Path) : AbsoluteHierPart {
        return AbsoluteHierPart(this, path)
    }

    operator fun plus(port: Port) : Authority {
        return Authority(this.userInfo, this.host, port)
    }

    override fun toString() : String {
        val userRaw = if(userInfo == UserInfo.EMPTY) "" else userInfo.toString() + "@"
        val portRaw = if(port == Port.DEFAULT || port == Port.EMPTY) "" else ":" + port.toString()
        return userRaw + host.toString() + portRaw
    }
}
