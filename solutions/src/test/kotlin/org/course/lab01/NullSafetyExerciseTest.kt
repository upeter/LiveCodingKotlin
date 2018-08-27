package org.course.lab01


import io.kotlintest.shouldBe
import io.kotlintest.specs.WordSpec

class NullSafetyExerciseTest : WordSpec() {
    init {
        "BookingConfirmation" should {
            val account = Account("Jack", "jack@away.com")
            val hotel = Hotel(name = "Holiday Out", address = "Beach Avenue, Los Vaagas", stars = "**** ")
            val destination = Destination("Costa del Sol", hotel)
            "create no message if mandatory fields are null" {
                NullableTypesExercise.confirmBooking(null, null) shouldBe null
                NullableTypesExercise.confirmBooking(null, Booking(3400, destination)) shouldBe null
                NullableTypesExercise.confirmBooking(account, null) shouldBe null
                NullableTypesExercise.confirmBooking(account, Booking(3400, null)) shouldBe null
            }
            "create confirmation message for all properties" {
                NullableTypesExercise.confirmBooking(account, Booking(3400, destination)) shouldBe
                        "Confirmation for: jack@away.com amount: 3400 hotel: Holiday Out **** at Beach Avenue, Los Vaagas"
            }
            "create confirmation message for hotel without stars" {
                val noStarHotel = Hotel(name = "Holiday Out", address = "Beach Avenue, Los Vaagas", stars = null)
                val noStarDestination = Destination("Costa del Sol", noStarHotel)
                NullableTypesExercise.confirmBooking(account, Booking(3400, noStarDestination)) shouldBe
                        "Confirmation for: jack@away.com amount: 3400 hotel: Holiday Out at Beach Avenue, Los Vaagas"
            }
            "create confirmation message for free holiday" {
                NullableTypesExercise.confirmBooking(account, Booking(amount = null, destination = destination)) shouldBe
                        "Confirmation for: jack@away.com amount: FREE hotel: Holiday Out **** at Beach Avenue, Los Vaagas"
            }
        }
    }
}
