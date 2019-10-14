package org.course.lab04

import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.full.functions
import kotlin.system.measureTimeMillis

class CoroutineExerciseTest : WordSpec() {
    companion object {
        val executorService = Executors.newScheduledThreadPool(3);
    }

    class CurrencyService(private val conversionRate: Int, val latency: Long) {
        suspend fun rateUSD(): Int {
            delay(latency)
            return conversionRate
        }

        fun rateUSD_Futures(): CompletableFuture<Int> =
                CompletableFuture<Int>().apply {
                    executorService.schedule({ complete(conversionRate) }, latency, TimeUnit.MILLISECONDS)
                }

    }

    suspend fun <T> ScheduledExecutorService.awaitScheduled(delay: Long, unit: TimeUnit, callable: () -> T) = suspendCoroutine<T> {
        schedule({ it.resumeWith(Result.success(callable())) }, delay, unit)
    }


    val serviceBankA = CurrencyService(120, 1000)
    val serviceBankB = CurrencyService(123, 2000)
    val serviceBankC = CurrencyService(125, 3000)
    val servicesBankABC = listOf(serviceBankA, serviceBankB, serviceBankC)


    init {
        "Exercise 1" should {
            "convert rateUSD into a suspend function" {
                //val method = CurrencyService::class.java.getMethod("rateUSD", Continuation::class.java)
                val suspended = CurrencyService::class.functions
                        .filter { it.name == "rateUSD" }
                        .all { it.isSuspend }
                suspended shouldBe true
            }
        }
        "Exercise 2" should {
            "call suspend function rateUSD of bankA" {
                val rateBankA = runBlocking {
                    serviceBankA.rateUSD()
                }
                rateBankA shouldBe 120
            }
        }
        "Exercise 3" should {
            "call rateUSD (suspended function) of all banks concurrently" {
                val ms = measureTimeMillis {
                    runBlocking {
                        val jobs = servicesBankABC.map {
                            launch {
                                it.rateUSD()
                            }
                        }
                        jobs.map { it.join() }
                    }
                }
                ms.toDouble() shouldBe (3000.toDouble().plusOrMinus(500.0))
            }
        }
        "Exercise 4" should {
            "get average conversion rates via rateUSD of all banks concurrently " {
                val ms = measureTimeMillis {
                    val averageConversionRate = runBlocking {
                        val results = servicesBankABC.map {
                            async {
                                it.rateUSD()
                            }
                        }
                        results.map { it.await() }.sum() / results.size
                    }
                    averageConversionRate shouldBe ((120 + 123 + 125) / 3)
                }
                ms.toDouble() shouldBe (3000.toDouble().plusOrMinus(500.0))
            }
        }
        "Exercise 5" should {
            "convert futures calls into coroutines" {
                val minRatesFutures = servicesBankABC.map { it.rateUSD_Futures() }.map { it.get() }.min()
                minRatesFutures shouldBe (120)
                runBlocking {
                    val minRateCoroutines = servicesBankABC.map { it.rateUSD_Futures().await() }.min()
                    minRateCoroutines shouldBe (120)
                }
            }
        }
        "Exercise 5 Bonus" should {
            "Transform ScheduledExecutorService.schedule method into suspended function" {
                runBlocking {
                    val ms = measureTimeMillis {
                        executorService.awaitScheduled(1000, TimeUnit.MILLISECONDS) { "hi" }
                    }
                    ms.toDouble() shouldBe (1000.toDouble().plusOrMinus(500.0))
                }
            }
        }
    }
}

