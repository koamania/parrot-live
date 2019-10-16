package com.leeda.parrot.parrot.reader

import com.leeda.parrot.parrot.reader.options.Align
import com.leeda.parrot.parrot.reader.options.ParrotReaderOption

data class ParrotFrame(
    private val data: String
) {
    private val reverse: String = data.reversed()
    private val flip: String = data.flip()

    fun getData(option: ParrotReaderOption): String = when {
        option.align == Align.flip -> flip
        option.align == Align.reverse -> reverse
        else -> data
    }

    private fun String.flip(): String {
        val splitStringIterator = this.split("\n").iterator()

        var flip: String = "";

        while(splitStringIterator.hasNext()) {
            val splitString = splitStringIterator.next()
            flip += splitString.reversed()
            if (splitStringIterator.hasNext()) {
                flip += System.getProperty("line.separator")
            }
        }

        return flip
    }
}