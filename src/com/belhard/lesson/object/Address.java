package com.belhard.lesson.object;

public class Address {
    private String citi;
    private String street;
    private int house;
    private int apartment;

    public Address(String citi, String street, int house, int apartment) {
        this.citi = citi;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public String getCiti() {
        return citi;
    }

    public String getStreet() {
        return street;
    }

    public int getHouse() {
        return house;
    }

    public int getApartment() {
        return apartment;
    }

    @Override
    public String toString() {
        return
                citi + " " + street + " " + house + " " + apartment;
    }
}
