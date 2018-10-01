package org.course.lab03

import io.kotlintest.matchers.collections.containExactlyInAnyOrder
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec
import org.course.lab03.ControlFlowExercise1.matchOnInputType
import org.course.lab03.ControlFlowExercise2.show
import org.course.lab03.ControlFlowExercise3.diffResult
import ch.qos.logback.classic.Logger as LogbackLogger

class ControlFlowExerciseTest : WordSpec() {
    init {
        "ControlFlowExercise1" should {
            "match a variety of branch conditions using when" {
                val matchUs = listOf("Kotlin" to "Literal Kotlin",
                        "Any String X" to "A string with length 12",
                        "Any String XYZ" to "A string with length 14",
                        20 to "A positive integer",
                        -2 to "A negative integer",
                        'k' to "A character in alphabet: k",
                        'o' to "A character in alphabet: o",
                        (1..100).toList() to "List with more than 10 element",
                        (1..9).toList() to "List with less than 10 element",
                        emptyList<Any>() to "empty List",
                        (null as Long?) to "nullable value",
                        {} to "The default")

                matchUs.forEach { (param, expected) -> matchOnInputType(param) shouldBe expected }
            }
        }
        "ControlFlowExercise2 And ControlFlowExercise3" should {
            class UserPreferences(val username: String, val newsLetter: Boolean, val platform: String? = null, val language: String? = null)

            val existing = UserPreferences("jack", false, null, "en_US")
            val new = UserPreferences("jack", true, "desktop", null)

            val prefChanges:List<PropChange<Any?>> = calcPropChanges(before = existing, after = new)
            "print destructured collection items with for loop in show(...)" {
                val printed = mutableListOf<String>()
                val printFun: (String) -> Unit = { s -> printed += s }
                show(prefChanges, printFun)
                printed should containExactlyInAnyOrder(listOf(
                        "language: was=en_US, is=null",
                        "newsLetter: was=false, is=true",
                        "platform: was=null, is=desktop",
                        "username: was=jack, is=jack"
                ))
            }
            "calculate property changes between old and new value in diffResult(...)" {
                val diff = prefChanges.map { it.prop to diffResult(it) }
                diff should containExactlyInAnyOrder(
                        "username" to "unchanged",
                        "newsLetter" to "changed",
                        "platform" to "added",
                        "language" to "removed"
                )

            }
        }
    }
}
