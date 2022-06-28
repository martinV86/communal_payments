package com.belhard.lesson.dao;

import com.belhard.lesson.object.Person;

import java.sql.Date;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;


public interface PaymentsDao {


    public String setPayments(int id, Person payer, LocalDate data, int check, int payments) throws ParseException;

    public String updatePayments(int id, Person payer, int check, int payments) throws ParseException;

    public String getPayments(LocalDate date);

    public String getDebtor(LocalDate date);

    public String getCreditor(LocalDate date);

    public ArrayList<String> getCommunal();


}
