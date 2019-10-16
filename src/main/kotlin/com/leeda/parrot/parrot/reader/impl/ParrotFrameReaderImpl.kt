package com.leeda.parrot.parrot.reader.impl

import com.leeda.parrot.parrot.reader.ParrotFrameDataSource
import com.leeda.parrot.parrot.reader.ParrotFrame
import com.leeda.parrot.parrot.reader.ParrotFrameReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader

@Component
class ParrotFrameReaderImpl(@Autowired private val frameSource: ParrotFrameDataSource): ParrotFrameReader {

    override fun readAll(): Array<ParrotFrame> {
        val sourceInputStreams = frameSource.getFrameInputStreams()
        val frames = ArrayList<ParrotFrame>()
        for (sourceInputStream in sourceInputStreams) {
            BufferedReader(InputStreamReader(sourceInputStream)).use {
                frames.add(ParrotFrame(it.readText()))
            }
        }

        return frames.toTypedArray()
    }
}