package com.leeda.parrot.parrot.reader

interface ParrotFrameReader {
    fun readAll(): Array<ParrotFrame>
}