package org.course.lab03


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
 * Define an extension method equals(...) on the root type of Kotlin's type hierarchy (Any?),
 * so that an equality check also works on Nullable types.
 */
fun Any?.equals(other: Any?): Boolean = this?.let { it.equals(other) } ?: other == null


/**
 * Exercise 4:
 * Define an extension method filterByType() on Iterable<Any>, which filters and returns all instances of the given type
 * as a List<T>.
 */
inline fun <reified T> Iterable<Any>.filterByType(): List<T> =
        this.filter { it is T }.map { it as T }

