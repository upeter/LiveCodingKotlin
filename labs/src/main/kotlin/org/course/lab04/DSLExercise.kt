package org.course.lab04

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * Exercise 1:
 * Create a money DSL which allows you to create Euro classes and perform calculations using natural language constructs:
 * - 40.cents							=> Euro(0, 40)
 * - 1.95.euro          				=> Euro(1, 95)
 * - 2.euro + 25.cents                  => Euro(2, 25)
 * - 1.euro + 20.cents + 4.25.euro * 4  => Euro(18, 20)
 *
 * The Euro data class is already provided as well as an extension method Double.toWholeNumberAndDecimalFraction()
 * that splits a Double into its whole number and decimal fraction.
 * E.g.: 2.34 => Pair(2, 34)
 */
data class Euro(val euro: Int, val cents: Int) {
    val inCents by lazy {
        euro * 100 + cents
    }

    companion object {
        fun fromCents(i: Int) = Euro(i / 100, i % 100)
    }
}

fun Double.toWholeNumberAndDecimalFraction(): Pair<Int, Int> {
    val bd = BigDecimal(this)
    val cents = bd.times(BigDecimal(100)).round(MathContext(3, RoundingMode.HALF_UP)).toInt()
    return cents / 100 to cents % 100
}


/**
 * Exercise 2:
 * Take a look at the data class Wallet. Enhance it so that the following statements are possible:
 * - Wallet(1.50.euro)
 * - Wallet(3.25.euro, 10.cents)
 * - Wallet(3.25.euro) union Wallet(4.75.euro)
 *
 * Important: the euros field of type List<Euro> in Wallet must not be changed.
 */
data class Wallet(val euros: List<Euro> = emptyList()) {
    val total = euros.reduce { cum, next -> Euro.fromCents(cum.inCents + next.inCents) }
}


