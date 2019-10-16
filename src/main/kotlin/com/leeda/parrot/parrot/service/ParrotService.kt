package com.leeda.parrot.parrot.service

import com.leeda.parrot.parrot.reader.options.ParrotReaderOption
import java.io.OutputStream

interface ParrotService {
    fun streamingParrot(os: OutputStream, option: ParrotReaderOption)
}