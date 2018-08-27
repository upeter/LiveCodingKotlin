package org.course.lab01;

public class ExhaustiveNullCheckSample {

    public String confirmationMessage(Account account, Booking booking) {
        if (booking != null) {
            String amount = booking.getAmount() != null ? booking.getAmount().toString() : "FREE";
            if (booking.getDestination() != null && booking.getDestination().getHotel() != null) {
                Hotel hotel = booking.getDestination().getHotel();
                String stars = "";
                if (hotel.getStars() != null) {
                    stars = hotel.getStars();
                }
                String address = hotel.getName() != null ? hotel.getName() : "" + " " + stars + "at " + hotel.getAddress() != null ? hotel.getAddress() : "";
                if (account != null && account.getEmail() != null) {
                    return createMessage(account.getEmail(), amount, address);
                }
            }
        }
        return null;

    }

    private String createMessage(String email, String amount, String hotel) {
        return "Confirmation for: " + email + " amount: " + amount + " hotel: " + hotel;
    }


    class Account {
        private final String username;
        private final String email;

        Account(String username, String email) {
            this.username = username;
            this.email = email;
        }

        public String getUsername() {
            return username;
        }

        public String getEmail() {
            return email;
        }
    }

    class Booking {
        private final Long amount;
        private final Destination destination;


        public Long getAmount() {
            return amount;
        }

        public Destination getDestination() {
            return destination;
        }


        Booking(Long amount, Destination destination) {
            this.amount = amount;
            this.destination = destination;
        }
    }

    class Destination {
        private final String airport;
        private final Hotel hotel;

        Destination(String airport, Hotel hotel) {
            this.airport = airport;
            this.hotel = hotel;
        }

        public String getAirport() {
            return airport;
        }

        public Hotel getHotel() {
            return hotel;
        }
    }

    class Hotel {
        private final String name;
        private final String address;
        private final String stars;

        Hotel(String name, String address, String stars) {
            this.name = name;
            this.address = address;
            this.stars = stars;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getStars() {
            return stars;
        }
    }


}
