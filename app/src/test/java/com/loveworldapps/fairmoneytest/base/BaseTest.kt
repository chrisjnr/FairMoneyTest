package com.loveworldapps.fairmoneytest.base

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest


/**
 * Created by manuelchris-ogar on 26/01/2021.
 */



abstract class BaseTest : KoinTest {


    lateinit var mMockServerInstance: MockWebServer


    private var mShouldStart = false

    @Before
    open fun setUp(){
        startMockServer(true)
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