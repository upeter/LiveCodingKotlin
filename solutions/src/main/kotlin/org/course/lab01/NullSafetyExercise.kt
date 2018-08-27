package org.course.lab01

object NullableTypesExercise {

    fun confirmBooking(account: Account?, booking: Booking?):String? {
        val email = account?.email
        val amount = booking?.amount?.toString() ?: "FREE"
        val hotel = booking?.destination?.hotel
        return if (email != null && amount != null && hotel != null) {
            val address = "${hotel.name} ${hotel.stars.orEmpty()}at ${hotel.address}"
            createMessage(account.email, amount, address)
        } else null
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


