package org.course.lab02

import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec


class CollectionExerciseTest : WordSpec() {
    init {
        "CollectionExercise01: GoogleCodeJam" should {
            "get first Element in list" {
                val in1 = "ejp mysljylc kd kxveddknmc re jsicpdrysi"
                val in2 = "rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd"
                val in3 = "de kr kd eoya kw aej tysr re ujdr lkgc jv"
                val out1 = "our language is impossible to understand"
                val out2 = "there are twenty six factorial possibilities"
                val out3 = "so it is okay if you want to just give up"

                val result = CollectionExercise01.googleCodeJamGooglerese(in1, in2, in3)
                listOf(out1, out2, out3) shouldBe result
            }
        }

        "CollectionExercise02" should {
            "group a list of adults by age group. Each group is sorted by name"  {
                val jack = Person(14, "Jack")
                val duke = Person(32, "Duke")
                val jeniffer = Person(34, "Jeniffer")
                val erik = Person(24, "Erik")
                val susy = Person(40, "Susy")
                val result = CollectionExercise02.groupAdultsPerAgeGroup(listOf(jack, duke, jeniffer, erik, susy))
                mapOf(20 to listOf(erik), 30 to listOf(duke, jeniffer), 40 to listOf(susy)) shouldBe result
            }
        }

        "CollectionExercise03" should {
            "check that each subsequent value in the sequence increases" {
                CollectionExercise03.checkValuesIncrease(listOf(1, 2, 3)) shouldBe true
                CollectionExercise03.checkValuesIncrease(listOf(1)) shouldBe true
                CollectionExercise03.checkValuesIncrease(listOf(1, 2, 100)) shouldBe true
                CollectionExercise03.checkValuesIncrease(listOf(1, 2, 1)) shouldBe false
            }
        }

        "CollectionExercise04" should {
            "calculate the length of the longest word in a list of sentences" {
                val l1 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque pharetra lorem ut sem feugiat tristique. "
                val l2 = "Etiam id magna ut libero ullamcorperano scelerisque. "
                val result = CollectionExercise04.calcLengthLongestWord(l1, l2)
                "ullamcorperano".length shouldBe result
            }
        }

        "CollectionExercise05" should {
            "use foldLeft for common higher order functions" {
                val input = listOf(1, 2, 3)
                input.filter { it % 2 == 0 } shouldBe CollectionExercise05.filterWithFold(input)
                input.groupBy { it % 2 == 0 } shouldBe CollectionExercise05.groupByWithFold(input)
            }
        }
    }
}
