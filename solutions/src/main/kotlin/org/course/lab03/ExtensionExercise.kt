package org.course.lab03

import java.util.Scanner


/**
 * Exercise 1:
 * Define an extension method square() on Int that calculates the square value
 */
fun Int.square() = this * this

/**
 * Exercise 2:
 * Define an extension property on String that returns the tail of the String.
 * The tail is the remaining part of the String new the first character.
 * Tip: the removePrefix(...) might be of good use...
 */
val String.tail: String
    get() = this.firstOrNull()?.let { this.removePrefix(it.toString()) } ?: this


/**
 * Exercise 3:
 * Define an extension method sameLength(...) on nullable String's (String?),
 * to check that they have the same length regardless whether they are null or not.
 */
fun String?.sameLength(other: String?): Boolean = this?.length == other?.length


/**
 * Exercise 4:
 * Define an extension method filterByType() on Iterable<Any>, which filters and returns all instances of the given type
 * as a List<T>.
 */
inline fun <reified T> Iterable<Any>.filterByType(): List<T> =
        this.filter { it is T }.map { it as T }

/**
 * Exercise 5:
 * 5a)
 * Replace the function parameter transform:(String) -> String of doWithText(...)  with a 'function literal with receiver' so that the caller of this
 * higher order method can use 'this' to reference the String rather than 'it'.
 *
 * 5b)
 * Refactor the implementation of the doWithText(...) method so that all operations on Scanner are executed in the same scope by
 * using one of Kotlin's scope functions.
 */
fun doWithText(transform: String.() -> String): String =
        Scanner(object {}.javaClass.getResourceAsStream("/text.txt")).run {
            useDelimiter("\\Z")
            next().transform()
        }
