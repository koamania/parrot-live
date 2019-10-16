package com.leeda.parrot.parrot.service.impl

import com.leeda.parrot.parrot.reader.ParrotFrame
import com.leeda.parrot.parrot.reader.ParrotFrameReader
import com.leeda.parrot.parrot.reader.options.ParrotReaderOption
import com.leeda.parrot.parrot.service.ParrotService
import com.leeda.parrot.utils.Color
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Service
class ParrotServiceImpl(
        @Autowired private val parrotFrameReader: ParrotFrameReader
) : ParrotService {

    private val parrotFrames: Array<ParrotFrame> by lazy {
        parrotFrameReader.readAll()
    }

    override fun streamingParrot(os: OutputStream, option: ParrotReaderOption) {
        val ow = OutputStreamWriter(os)

        val colors: Array<Color> = Color.values()

        fun selectColor(previous: Color): Color {
            var newColor: Color
            do {
                newColor = colors[Random.nextInt(colors.size)]
            } while (newColor == previous)

            return newColor
        }

        ow.run {
            while (true) {

                var lastColor: Color = colors[0]
                try {
                    for (parrotFrame in parrotFrames) {

                        this.write("\u001b[2J\u001b[H")
                        val newColor = selectColor(lastColor)
                        this.write(newColor.ansiCode)

                        this.write(parrotFrame.getData(option))
                        this.flush()
                        TimeUnit.MILLISECONDS.sleep(70)
                        lastColor = newColor

                    }
                } catch (e: IOException) {
                    println("closed stream")
                    break
                }
            }
        }
    }
}