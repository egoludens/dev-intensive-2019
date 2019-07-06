package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    return if (this.trimEnd().length <= length) this.trimEnd() else "${this.substring(0, length).trimEnd()}..."
}