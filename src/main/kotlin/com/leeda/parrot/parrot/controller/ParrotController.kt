package com.leeda.parrot.parrot.controller

import com.leeda.parrot.parrot.reader.options.ParrotReaderOption
import com.leeda.parrot.parrot.service.ParrotService
import eu.bitwalker.useragentutils.Browser
import eu.bitwalker.useragentutils.UserAgent
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody
import java.io.OutputStream

@Controller
@RequestMapping
class ParrotController(
        private val parrotService: ParrotService
) {

    @GetMapping("/")
    fun parrot(options: ParrotReaderOption): StreamingResponseBody? {

        val request = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request
        val response = (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).response!!
        val userAgent: UserAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"))

        if (userAgent.browser != Browser.DOWNLOAD) {
            response.status = 302
            response.setHeader("Location", "https://github.com/koamania/parrot-live")
            return null
        }

        return ParrotStreaming(options)
    }

    inner class ParrotStreaming(private val options: ParrotReaderOption) : StreamingResponseBody {
        override fun writeTo(os: OutputStream) {
            parrotService.streamingParrot(os, options)
        }
    }
}