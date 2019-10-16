package com.leeda.parrot.parrot.reader.impl

import com.leeda.parrot.parrot.reader.ParrotFrameDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.support.ResourcePatternUtils
import org.springframework.stereotype.Component
import java.io.InputStream
import java.util.*

@Component
class FileParrotFrameDataSource(
        @Autowired private val resourceLoader: ResourceLoader
) : ParrotFrameDataSource {

    override fun getFrameInputStreams(): ArrayList<InputStream> {
        val resources = this.getParrotResources()
        resources.sortBy { resource -> resource.filename }
        val fileInputStreamList = ArrayList<InputStream>()
        for (resource in resources) {
            fileInputStreamList.add(resource.inputStream)
        }
        return fileInputStreamList
    }

    private fun getParrotResources(): Array<Resource> = ResourcePatternUtils.getResourcePatternResolver(resourceLoader)
            .getResources("classpath:frames/*.parrot")


}