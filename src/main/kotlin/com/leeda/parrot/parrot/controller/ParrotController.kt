package com.leeda.parrot.parrot.controller

import com.leeda.parrot.parrot.reader.options.ParrotReaderOption
import com.leeda.parrot.parrot.service.ParrotService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.OutputStream

@Controller
@RequestMapping
class ParrotController(
        private val parrotService: ParrotService
) {

    @GetMapping("/")
    fun parrot(options: ParrotReaderOption): StreamingResponseBody {
        return ParrotStreaming(options)
    }

    inner class ParrotStreaming(private val options: ParrotReaderOption) : StreamingResponseBody {
        override fun writeTo(os: OutputStream) {
            parrotService.streamingParrot(os, options)
        }
    }
}