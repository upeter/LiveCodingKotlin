package org.course.lab03

import io.kotlintest.matchers.collections.containExactly
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import ch.qos.logback.classic.Logger as LogbackLogger

class ExtensionExerciseTest : WordSpec() {
    init {

        "ExtensionExerciseTest" should {
            "have Int extended with a square() method" {
                2.square() shouldBe 4
            }
            "have String extended with a tail property" {
                "Cool".tail shouldBe "ool"
                "A".tail shouldBe ""
                "".tail shouldBe ""
            }
            "have Any? extended with an equals method" {
                class Foo()
                val foo:Foo? = Foo()
                val noFoo:Foo? = null
                noFoo.equals(noFoo) shouldBe true
                null.equals(noFoo) shouldBe true
                noFoo.equals(2) shouldBe false
                noFoo.equals(foo) shouldBe false
            }
            "have Iterable<Any> extended with an filterByType method" {
                val l = listOf("a", 2, 3.0, {})
                l.filterByType<Number>() should containExactly(2, 3.0)
                l.filterByType<String>() should containExactly("a")

            }

        }
    }
}
