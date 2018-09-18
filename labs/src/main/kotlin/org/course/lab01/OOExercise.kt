package org.course.lab01

/**
 * The goal of this exercise is to get familiar basic OO constructs in Kotlin
 *
 * Fix the code so that the unit test 'CurrencyExerciseTest' passes.
 *
 * In order for the tests to pass you need to do the following:
 *
 * Exercise 1:
 * - Create a class Euro
 * - Provide it with two constructor parameters: euro:Int, cents:Int
 * - Provide the cents field with default value: 0
 * - Provide an immutable field named: inCents that converts euro + cents into cents.
 * - Create an object Euro with a factory method named: fromCents that creates an Euro based on cents.
 * - Create a method named: plus to the Euro class that adds another Euro
 * - Create a method named: multiply to the Euro class that multiplies an Euro
 *
 * Exercise 2:
 * - Create an abstract class Currency
 * - Provide it with one constructor parameter: symbol:Symbol
 * - Symbol is an Enum with constants for EUR and USD and an immutable field: sign:String
 * - Extend the previously created Euro class from Currency
 * - Override the toString method of Euro to represent the following String:
 *   -> symbol.sign + ': ' + euro + ',' + cents.  E.g: € 200,05
 * - In case the cents are 0 use this representation:
 *   -> symbol.sign + ': ' + euro + ',--. E.g.: € 200.--
 */

class Euro {

}