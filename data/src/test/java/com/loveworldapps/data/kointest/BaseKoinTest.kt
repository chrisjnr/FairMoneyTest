package com.loveworldapps.data.kointest


import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import java.io.File
import java.net.URL


abstract class BaseKoinTest : KoinTest {


     lateinit var mMockServerInstance: MockWebServer


    private var mShouldStart = false

    @Before
    open fun setUp(){
        startMockServer(true)
    }

    /**
     * Helps to read input file returns the respective data in mocked call
     */
    fun mockNetworkResponseWithFileContent(fileName: String, responseCode: Int) = mMockServerInstance.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName)))

    /**
     * Reads input file and converts to json
     */
    @Suppress("UnstableApiUsage")
    fun getJson(path : String) : String {
        val uri: URL = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

    /**
     * Start Mockwebserver
     */
    private fun startMockServer(shouldStart:Boolean){
        if (shouldStart){
            mShouldStart = shouldStart
            mMockServerInstance = MockWebServer()
            mMockServerInstance.start()
        }
    }

    /**
     * Set Mockwebserver url
     */
    fun getMockWebServerUrl() = mMockServerInstance.url("/").toString()

    /**
     * Stop Mockwebserver
     */
    private fun stopMockServer() {
        if (mShouldStart){
            mMockServerInstance.shutdown()
        }
    }

    @After
    open fun tearDown(){
        //Stop Mock server
        stopMockServer()
        //Stop Koin as well
        stopKoin()
    }
}