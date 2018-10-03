package org.course.lab03

import io.kotlintest.specs.WordSpec
import ch.qos.logback.classic.Logger as LogbackLogger

class ExtensionExerciseTest : WordSpec() {
    init {

        "ExtensionExerciseTest" should {
            "have Int extended with a square() method" {
                TODO("uncomment next lines")
                //2.square() shouldBe 4
            }
            "have String extended with a tail property" {
                TODO("uncomment next lines")
//                "Cool".tail shouldBe "ool"
//                "A".tail shouldBe ""
//                "".tail shouldBe ""
            }
            "have Any? extended with an equals method" {
                class Foo()
                val foo:Foo? = Foo()
                val noFoo:Foo? = null
                TODO("uncomment next lines")
//                noFoo.equals(noFoo) shouldBe true
//                null.equals(noFoo) shouldBe true
//                noFoo.equals(2) shouldBe false
//                noFoo.equals(foo) shouldBe false
            }
            "have Iterable<Any> extended with an filterByType method" {
                TODO("uncomment next lines")
                val l = listOf("a", 2, 3.0, {})
//                l.filterByType<Number>() should containExactly(2, 3.0)
//                l.filterByType<String>() should containExactly("a")

            }

        }
    }
}
