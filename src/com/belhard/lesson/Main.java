package com.belhard.lesson;
import java.sql.Date;
import java.util.ArrayList;

import com.belhard.lesson.dao.PaymentsBaseDao;
import com.belhard.lesson.object.Address;
import com.belhard.lesson.object.Person;
import com.belhard.lesson.servise.Servise;
import com.belhard.lesson.util.ParsenUtilPayments;

import javax.xml.crypto.Data;


public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<String>communal;
        PaymentsBaseDao paymentsBaseDao;
        ParsenUtilPayments parsenUtilPayments;
//        PaymentsBaseDao paymentsBaseDao=new PaymentsBaseDao(6,(new Person("Вероника","Мартин",
//                (new Address("Минск","Аэродромная",28,127)))),"2022-05-14",100,100,Servise.UPDATE);
//         paymentsBaseDao=new PaymentsBaseDao("2022-05-14",Servise.GET_CREDITOR);
//        parsenUtilPayments=new ParsenUtilPayments("payments.txt","Должники: ",paymentsBaseDao.getCommunal());
//         paymentsBaseDao=new PaymentsBaseDao("2022-05-14",Servise.GET);
//        parsenUtilPayments=new ParsenUtilPayments("payments.txt","Хорошие плательшики: ",paymentsBaseDao.getCommunal());
         paymentsBaseDao=new PaymentsBaseDao("2022-05-14",Servise.GET_DEBTOR);
        parsenUtilPayments=new ParsenUtilPayments("payments.txt","Спонсоры: ",paymentsBaseDao.getCommunal());



    }
}
