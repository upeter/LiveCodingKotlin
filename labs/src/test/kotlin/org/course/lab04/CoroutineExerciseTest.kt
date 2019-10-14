package org.course.lab04

import io.kotlintest.matchers.doubles.plusOrMinus
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.util.concurrent.*
import kotlin.concurrent.thread
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.full.functions
import kotlin.system.measureTimeMillis

class CoroutineExerciseTest : WordSpec() {
    companion object {
        val executorService = Executors.newScheduledThreadPool(3);
    }

    class CurrencyService(private val conversionRate: Int, val latency: Long) {

        fun rateUSD(): Int {
            Thread.sleep(latency)
            return conversionRate
        }

        fun rateUSD_Futures(): CompletableFuture<Int> =
                CompletableFuture<Int>().apply {
                    executorService.schedule({ complete(conversionRate) }, latency, TimeUnit.MILLISECONDS)
                }

    }

    /**
     * Exercise 5
     * Turn the awaitScheduled(...) method into a suspend method which calls the ScheduledExecutionService' schedule(...) method
     * (see implementation of rateUSD_Futures). Resume the Continuation when the scheduler fires with the value of the
     * callable:() -> T function.
     */
    suspend fun <T> ScheduledExecutorService.awaitScheduled(delay: Long, unit: TimeUnit, callable: () -> T):T = TODO()


    val serviceBankA = CurrencyService(120, 1000)
    val serviceBankB = CurrencyService(123, 2000)
    val serviceBankC = CurrencyService(125, 3000)
    val servicesBankABC = listOf(serviceBankA, serviceBankB, serviceBankC)


    init {
        "Exercise 1" should {
            "convert rateUSD into a suspend function" {
                val suspended = CurrencyService::class.functions
                        .filter { it.name == "rateUSD" }
                        .all { it.isSuspend }
                suspended shouldBe true
            }
        }
        "Exercise 2" should {
            "call suspend function rateUSD of bankA" {
                val rateBankA:Int = TODO("Call the serviceBankA.rateUSD() method using an appropriate Coroutine builder method")
                rateBankA shouldBe 120

            }
        }
        "Exercise 3" should {
            "a) call rateUSD (suspended function) of all banks concurrently" {
                val ms = measureTimeMillis {
                    TODO("Call rateUSD() of all banks services (serviceBank[A-C]) *concurrently*. No return type is needed.")
                }
                ms.toDouble() shouldBe (3000.toDouble().plusOrMinus(500.0))
            }
            "b) get average conversion rates via rateUSD of all banks concurrently " {
                val ms = measureTimeMillis {
                    val averageConversionRate:Int = TODO(
                            "Call rateUSD() of all banks services (serviceBank[A-C]) *concurrently* " +
                                    "and calculate the average conversion rate.")
                    averageConversionRate shouldBe ((120 + 123 + 125) / 3)
                }
                ms.toDouble() shouldBe (3000.toDouble().plusOrMinus(500.0))
            }
        }
        "Exercise 4" should {
            "convert futures calls into coroutines" {

                    val minRateCoroutines = TODO(
                            "Call the future version (rateUSD_Futures()) of all bank services serviceBank[A-C] " +
                                    "using Coroutines and calculate the lowest rate.")
                    minRateCoroutines shouldBe (120)
            }
        }
        "Exercise 5" should {
            "Transform ScheduledExecutorService.schedule method into a suspended function" {
                runBlocking {
                    val ms = measureTimeMillis {
                        TODO("Implement awaitScheduled")
                        executorService.awaitScheduled(1000, TimeUnit.MILLISECONDS) { "hi" }
                    }
                    ms.toDouble() shouldBe (1000.toDouble().plusOrMinus(500.0))
                }
            }
        }
    }
}

