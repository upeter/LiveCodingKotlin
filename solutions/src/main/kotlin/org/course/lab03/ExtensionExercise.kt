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
 * Tip: String’s drop(...) method might be of good use...
 */
val String.tail: String
    get() {
        val first = firstOrNull()
        return if (isEmpty()) this else this.drop(1)
    }

/**
 * Exercise 3:
 * Define an extension property tail on String that returns the remaining part of the String
 * after the first character or an empty String if no character(s) is/are available.
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

