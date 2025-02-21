package com.diego.matesanz.idealista.ui.common.utils

fun Int.formatIntegerWithDots(): String {
    val numberString = this.toString()
    val formattedNumber = StringBuilder()
    var count = 0
    for (i in numberString.length - 1 downTo 0) {
        formattedNumber.insert(0, numberString[i])
        count++
        if (count % 3 == 0 && i != 0) {
            formattedNumber.insert(0, ".")
        }
    }
    return formattedNumber.toString()
}
