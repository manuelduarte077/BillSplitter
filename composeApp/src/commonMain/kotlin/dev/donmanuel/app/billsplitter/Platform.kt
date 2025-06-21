package dev.donmanuel.app.billsplitter

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform