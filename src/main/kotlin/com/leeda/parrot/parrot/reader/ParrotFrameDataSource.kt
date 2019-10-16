package com.leeda.parrot.parrot.reader

import java.io.InputStream
import java.util.ArrayList

interface ParrotFrameDataSource {
    fun getFrameInputStreams(): ArrayList<InputStream>
}