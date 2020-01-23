package org.course.lab03

import java.util.*


/**
 * Exercise 1:
 * Define an extension method square() on Int that calculates the square value
 */

/**
 * Exercise 2:
 * Define an extension *field* tail on String that returns the remaining part of the String
 * after the first character or an empty String if no character(s) is/are available.
 * Tip: String’s drop(...) method might be of good use...
 */


/**
 * Exercise 3:
 * Define an extension method sameLength(other:String):Boolean on nullable String's (String?)
 * to check that they have the same length regardless whether they are null or not.
 */


/**
 * Exercise 4:
 * Define an extension method filterByType() on Iterable<Any>, which filters and returns all instances of the given type
 * as a List<T>.
 */

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
fun doWithText(transform: (String) -> String): String {
    val scanner = Scanner(object {}.javaClass.getResourceAsStream("/text.txt"))
    scanner.useDelimiter("\\Z")
    return transform(scanner.next())
}
