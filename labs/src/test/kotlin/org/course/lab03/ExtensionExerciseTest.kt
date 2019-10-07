package org.course.lab03

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import ch.qos.logback.classic.Logger as LogbackLogger

class ExtensionExerciseTest : WordSpec() {
    init {

        "ExtensionExerciseTest" should {
            "Exercise 1: have Int extended with a square() method" {
                TODO("uncomment next lines")
                //2.square() shouldBe 4
            }
            "Exercise 2: have String extended with a tail property"  {
                TODO("uncomment next lines")
//                "Cool".tail shouldBe "ool"
//                "A".tail shouldBe ""
//                "".tail shouldBe ""
            }
            "Exercise 3: have String? extended with a sameLength(...) method" {
                val foo: String? = "foo"
                val noFoo: String? = null
                TODO("uncomment next lines")
//                foo.sameLength(foo) shouldBe true
//                noFoo.sameLength(noFoo) shouldBe true
//                noFoo.sameLength(foo) shouldBe false
//                foo.sameLength(noFoo) shouldBe false
            }
            "Exercise 4: have Iterable<Any> extended with an filterByType method" {
                val l = listOf("a", 2, 3.0, {})
                TODO("uncomment next lines")
//                l.filterByType<Number>() should containExactly(2, 3.0)
//                l.filterByType<String>() should containExactly("a")

            }
            "Exercise 5: have doWithText(...) use a 'function literal with receiver' as argument" {
                TODO("uncomment next lines")
//                val txt = doWithText { toUpperCase() }
//                txt.all { !it.isLowerCase() } shouldBe(true)
            }

        }
    }
}
