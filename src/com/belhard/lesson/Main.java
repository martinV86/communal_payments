package com.belhard.lesson;

import com.belhard.lesson.dao.PaymentsBaseDao;
import com.belhard.lesson.object.Address;
import com.belhard.lesson.object.Person;
import com.belhard.lesson.servise.Servise;

import javax.xml.crypto.Data;

public class Main {

    public static void main(String[] args) throws Exception {

        PaymentsBaseDao paymentsBaseDao=new PaymentsBaseDao(1,(new Person("Василий","Мартин",
                (new Address("Минск","Аэродромная",28,127)))),"2022-10-23",152,152,Servise.SET);
    }
}
