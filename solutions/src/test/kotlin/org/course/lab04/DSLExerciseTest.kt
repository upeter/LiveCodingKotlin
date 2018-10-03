package org.course.lab04

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import ch.qos.logback.classic.Logger as LogbackLogger

class DSLExerciseTest : WordSpec() {
    init {
        "DSLExercise1" should {
            "create euros with a DSL" {
                5.euro shouldBe Euro(5, 0)
                40.cents shouldBe Euro(0, 40)
                532.cents shouldBe Euro(5, 32)
                1.95.euro shouldBe Euro(1, 95)

            }
            "perform euro calculations with DSL" {
                2.euro + 25.cents                  shouldBe  Euro(2, 25)
                2.euro - 25.cents                  shouldBe  Euro(1, 75)
                1.50.euro * 5                  shouldBe  Euro(7, 50)
                1.euro + 20.cents + 4.25.euro * 4  shouldBe  Euro(18, 20)
            }
        }
        "DSLExercise2" should {
            "create a Wallet with variable number of arguments" {
                Wallet(1.euro, 2.cents).total shouldBe 1.02.euro
            }
            "combine two Wallets" {
                val combined = Wallet(1.euro, 20.cents) union Wallet(1.50.euro)
                combined.total shouldBe 2.70.euro
            }
        }
    }
}
