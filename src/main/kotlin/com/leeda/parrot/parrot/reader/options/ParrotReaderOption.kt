package com.leeda.parrot.parrot.reader.options

data class ParrotReaderOption(
        val align: Align = Align.normal
)

enum class Align {
    normal,
    reverse,
    flip
}