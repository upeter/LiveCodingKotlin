package org.course.lab03


/**
 * Exercise 1:
 * Define an Extension method on Int that calculates the square value
 */

fun Int.square() = this * this

/**
 * Exercise 2:
 * Define an Extension property on String that returns the tail of the String.
 * The tail is the remaining part of the String after the first character.
 * Tip: the removePrefix(...) might be of good use...
 */
val String.tail: String
    get() =
        if (this.firstOrNull() == null) this
        else this.removePrefix(this.first().toString())


/**
 * Exercise 3:
 * Define an Extension method equals(...) on the root type of Kotlin's type hierarchy (Any?),
 * so that an equality check also works on Nullable types.
 */
fun Any?.equals(other:Any?):Boolean = if(this != null) this.equals(other) else other == null


/**
 * Exercise 4:
 * Define an Extension method filterByType(...) on Iterable<Any>, which filters and returns all instances of the given type
 * as a List.
 */
inline fun <reified T> Iterable<Any>.filterByType(): List<T> =
    this.filter { it is T }.map { it as T }

