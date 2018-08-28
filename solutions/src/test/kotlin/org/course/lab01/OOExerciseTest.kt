package org.course.lab01


import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class OOExerciseTest : WordSpec() {
    init {
        "Exercise 1: Creating an Euro" should {
            "be initialized correctly" {
                val e = Euro(1, 5)
                e.euro shouldBe 1
                e.cents shouldBe 5

            }
            "have correct default value for cents" {
                val e = Euro(2)
                e.euro shouldBe 2
                e.cents shouldBe 0
            }
            "convert amount correctly to cents" {
                val e = Euro(2, 45)
                e.inCents shouldBe 245
            }
            "be created by cents" {
                val e = Euro.fromCents(245)
                e.euro shouldBe 2
                e.cents shouldBe 45
            }
            "add another euro correctly" {
                val res = Euro(1, 50).plus(Euro(2, 70))
                res.euro shouldBe 4
                res.cents shouldBe 20
            }
            "multiply correctly by a factor" {
                val res = Euro(1, 50).times(3)
                res.euro shouldBe 4
                res.cents shouldBe 50
            }
        }
        "Exercise 2: an Euro" should {
            "be a subclass of Currency" {
                val e: Currency = Euro(2, 5)
                e.symbol shouldBe Symbol.EUR
            }
            "have correct toString representation" {
                val e = Euro(2, 5)
                e.toString() shouldBe "€: 2,05"
                val e2 = Euro(2)
                e2.toString() shouldBe "€: 2,--"
            }
        }
    }
}
