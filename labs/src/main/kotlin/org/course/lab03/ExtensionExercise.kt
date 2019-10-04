package org.course.lab03

import java.util.*


/**
 * Exercise 1:
 * Define an extension method square() on Int that calculates the square value
 */

/**
 * Exercise 2:
 * Define an extension *property* tail on String that returns the remaining part of the String after the first character.
 * Tip: the removePrefix(...) might be of good use...
 */


/**
 * Exercise 3:
 * Define an extension method equals(...) on the root type of Kotlin's type hierarchy (Any?),
 * so that an equality check also works on Nullable types.
 */


/**
 * Exercise 4:
 * Define an extension method filterByType() on Iterable<Any>, which filters and returns all instances of the given type
 * as a List<T>.
 */

/**
 * Exercise 5:
 * 5a)
 * Replace the function parameter transform:(String) -> String of doWithText(...)  with a 'function type with receiver' so that the caller of this
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
