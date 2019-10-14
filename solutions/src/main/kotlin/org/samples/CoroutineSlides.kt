package org.samples

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.suspendCoroutine

fun main(args:Array<String>) = runBlocking{

    val job = GlobalScope.launch {
        delay(100)
        println("Hi")
    }
    println("Bonjour")
    job.join()

}


