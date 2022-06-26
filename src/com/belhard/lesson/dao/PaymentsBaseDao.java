package com.belhard.lesson.dao;

import com.belhard.lesson.object.Person;
import com.belhard.lesson.servise.Servise;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

public class PaymentsBaseDao implements PaymentsDao {
    public Connection c;
    public Statement statement;

    public PaymentsBaseDao(int id, Person payer, String data, int check, int payments, Servise servise) throws Exception {
        Properties properties = new Properties();
//        FileOutputStream out=new FileOutputStream("my properties");
//        properties.store(out,"commit");
        properties.load(new FileInputStream("my properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String db = properties.getProperty("db");
        c = DriverManager.getConnection(db, user, password);
        c.setAutoCommit(false);
        System.out.println("connection is successful");
        statement = c.createStatement();
//        String qs ="create Table PaymentsTable"+"(num_id int primary key not null,"+"data_payments date,"+"check_ int,"+"payments int);";
//        statement.executeUpdate(qs);
//        c.commit();
//        qs="create Table PayersTable"+"(num_id int primary key not null,"+" name varchar(20),"+"family_name varchar(20),"+"address varchar(50));";
//        statement.executeUpdate(qs);
//        c.commit();
        switch (servise) {
            case SET -> {
                statement.executeUpdate(setPayments(id, payer, data, check, payments));
                c.commit();
                break;
            }
            case UPDATE -> {
                statement.executeUpdate(updatePayments(id, payer, check, payments));
                c.commit();
                break;
            }
            case GET -> {
                ResultSet rs = statement.executeQuery(getPayments());
                while (rs.next()) {
                    int num_id = rs.getInt("num_id");
                    String num_data = String.valueOf(rs.getDate("data_payments"));
                    int num_check = rs.getInt("check_");
                    int num_payments = rs.getInt("payments");
                    String num_name = rs.getString("name");
                    String num_family_name = rs.getString("family_name");
                    String num_address = rs.getString("address");
                    System.out.println(num_id + " " + num_data + " " + num_check + " " + num_payments + " " + num_name + " " + num_family_name + " " + num_address);
                break;
                }
            }
        }
    }


    @Override
    public String setPayments(int id, Person payer, String data, int check, int payments) throws ParseException {
       
//        DateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
//        Date data_=dateFormat.parse(data);
        LocalDate data_=LocalDate.parse(data);
        System.out.println(data_);
        String t=String.valueOf(data_);
        String qs = "insert into PaymentsTable (num_id, data_payments, check_, payments)" + "values(" + id + "," + data_+ "," +
                check + "," + payments + ");" + "insert into PayersTable(num_id, name, family_name, address)" + "values(" +
                payer.getName() + "," + payer.getFamily_name() + "," + payer.getAddress().toString() + ");";
        return qs;
    }


    @Override
    public String updatePayments(int id, Person payer, int check, int payments) throws ParseException {

        String qs = "update PaymentsTable set check_=" + check + ", payments=" + payments + "where num_id=" + id + ";"
                + "update PayersTable set name=" + payer.getName() + ", family_name=" + payer.getFamily_name() + ", address=" + payer.getAddress().toString() + "where num_id=" + id + ";";

        return qs;

    }

    @Override
    public String getPayments() {
        String qs = "select num_id, data_payments, check_, payments, name, family_name, address" + "from PaymentsTable, PayersTable";
        return qs;
    }
}
