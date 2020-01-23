package org.course.lab02

import java.util.ArrayList



/**
 * This Lab contains exercises where the usage of
 * higher order collection methods can be rehearsed.
 */

object CollectionExercise01 {

    /**
     * Taken from: <a href="http://code.google.com/codejam/contest/1460488/dashboard">Problem A. Speaking in Tongues</a>
     *
     * Problem
     * The aim of this task is to translate a language into a new language called Googlerese.
     * To translate we take any message and replace each English letter with another English letter.
     * This mapping is one-to-one and onto, which means that the same input letter always gets replaced
     * with the same output letter, and different input letters always get replaced with different output letters.
     * A letter may be replaced by itself. Spaces are left as-is.
     *
     * For example (and here is a hint!), the translation algorithm includes the following three mappings:
     * 'a' -> 'y', 'o' -> 'e', and 'z' -> 'q'. This means that "a zoo" will become "y qee".
     *
     * Sample Input/Output
     * Input:
     * Case 1: ejp mysljylc kd kxveddknmc re jsicpdrysi
     * Case 2: rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
     * Case 3: de kr kd eoya kw aej tysr re ujdr lkgc jv
     *
     * Output:
     * Case 1: our language is impossible to understand
     * Case 2: there are twenty six factorial possibilities
     * Case 3: so it is okay if you want to just give up
     *
     */
    fun googleCodeJamGooglerese(vararg lines: String): List<String> {
        TODO("fix me")
    }
}
/*========================================================== */

class Person(val age: Int, val name: String)

object CollectionExercise02 {

    /**
     * Take a look at the java class: {@link ImperativeSample}. The
     * groupAdultsPerAgeGroup is implemented using an imperative programming
     * style.
     * Rewrite the method groupAdultsPerAgeGroup in the ImperativeSample java class
     * using a functional approach.
     */
    fun groupAdultsPerAgeGroup(persons: List<Person>): Map<Int, List<Person>> {
        TODO("fix me")
    }
}

/*========================================================== */

object CollectionExercise03 {
    /**
     * Create a method that checks that each subsequent value is greater than
     * the previous one.
     * E.g.:
     * checkValuesIncrease(listOf1,2,3)) == true
     * checkValuesIncrease(listOf1,2,2)) == false
     */
    fun <T:Comparable<T>> checkValuesIncrease(seq: List<T>): Boolean =
    TODO("fix me")

}
/*========================================================== */

object CollectionExercise04 {
    /**
     * Calculate the length of the longest word in a list of sentences.
     * To keep it simple it's ok to use String.split to extract all words of a sentence.
     */
    fun calcLengthLongestWord(vararg lines: String): Int {
        TODO("fix me")
    }
}

/*========================================================== */

object CollectionExercise05 {
    /**
     * Filter all even numbers of the given sequence using fold.
     * E.g. listOf1,2,3) is [2])
     */
    fun filterWithFold(seq: List<Int>): List<Int> {
        TODO("fix me")
    }

    /**
     * Group all numbers based on whether they are even or odd using fold.
     * For even use 'true' for odd use 'false'.
     * E.g: listOf1,2,3) becomes mapOf(true=[2]), false=[1,3])
     */
    fun groupByWithFold(seq: List<Int>): Map<Boolean, List<Int>> {
        TODO("fix me")
    }
}


