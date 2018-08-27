package org.course.lab01

import io.kotlintest.be
import io.kotlintest.should
import io.kotlintest.shouldThrow
import io.kotlintest.specs.WordSpec

/**
 * 1. Implement the divide method in Euro that has the following signature:
 * - fun div(divider:Int):Euro
 * -- If the divider is <=0 throw an IllegalArgumentException

 *  2. Write a KotlinTest covering the following testcases:
 * - Happy flow (divider is > 0)
 * - Alternative flow (divider is <= 0)
 */
class KotlinTestExerciseTest : WordSpec() {
    init {
        "Euro" should {
            "should be divisible" {
                val result = Euro(1, 20) / 5
                result.euro should be(0)
                result.cents should be(24)
            }

            "must produce an IllegalArgumentException if divided with <= 0" {
                shouldThrow<IllegalArgumentException> {
                    Euro (1, 2) / 0
                }
            }

        }
    }

}
