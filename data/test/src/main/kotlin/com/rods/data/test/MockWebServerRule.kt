package com.rods.data.test

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.junit.runners.model.Statement

annotation class MockWebServerResponsePath(val path: String)

class MockWebServerRule: TestWatcher() {

    private val mockWebServer = MockWebServer()
    fun provideUrl(path: String = "/"): HttpUrl = mockWebServer.url(path)

    override fun starting(description: Description) {
        super.starting(description)
        mockWebServer.start()

        val filePath = description.getAnnotation(MockWebServerResponsePath::class.java)?.path
        val javaClass = description.testClass

        filePath?.let {
            mockWebServer.enqueue(
                MockResponse().setBody(readTestResourceFile(javaClass, it))
            )
        }
    }

    override fun apply(base: Statement, description: Description): Statement {
        return super.apply(base, description)
    }

    override fun finished(description: Description) {
        super.finished(description)
        mockWebServer.shutdown()
    }

}