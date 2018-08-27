package org.course.lab01

/**
 * Take a look at the *java* class: {@link ExhaustiveNullCheckSample}. The
 * Port the code of the Java's confirmationMessage method to Kotlin making use Kotlin null-safety features.
 * The domain classes are already ported to Kotlin. Take a look at them below.
 * As an extra challenge ensure that you only use a single if expression in your implementation.
 * You completed this exercise when the corresponding unittest succeeds.
 */
object NullableTypesExercise {

    fun confirmationMessage(account: Account?, booking: Booking?):String? {
        TODO("implement from ExhaustiveNullCheckSample.java using nullable types")
    }

    private fun createMessage(email: String, amount: String, hotel: String) =
        "Confirmation for: $email amount: $amount hotel: $hotel"

}


class Account(val username: String,
              val email: String? = null
)

class Booking(
        val amount: Long? = null,
        val destination: Destination? = null
)


class Destination(
        val airport: String,
        val hotel: Hotel? = null
)

class Hotel(
        val name: String,
        val address: String,
        val stars: String? = null)


