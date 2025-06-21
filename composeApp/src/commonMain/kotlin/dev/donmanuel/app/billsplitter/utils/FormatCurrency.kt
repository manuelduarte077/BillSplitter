package dev.donmanuel.app.billsplitter.utils

import kotlin.math.absoluteValue
import kotlin.math.round

fun Double.formatCurrency(): String {
    val rounded = round(this * 100) / 100
    val intPart = rounded.toInt()
    val decimalPart = ((rounded - intPart) * 100).toInt().absoluteValue

    return "$intPart.${decimalPart.toString().padStart(2, '0')}"
}