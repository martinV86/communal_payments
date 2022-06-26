package com.belhard.lesson.object;

public class Person {
    private String name;
    private String family_name;
    private Address address;

    public Person(String name, String family_name, Address address) {
        this.name = name;
        this.family_name = family_name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getFamily_name() {
        return family_name;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", family_name='" + family_name + '\'' +
                ", address=" + address.toString() +
                '}';
    }
}
