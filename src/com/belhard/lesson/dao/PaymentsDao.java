package com.belhard.lesson.dao;

import com.belhard.lesson.object.Person;

import java.text.ParseException;

public interface PaymentsDao {


    public String setPayments(int id, Person payer, String data, int check, int payments) throws ParseException;

    public String updatePayments(int id, Person payer,  int check, int payments) throws ParseException;
    public String getPayments();
}
