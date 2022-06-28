package com.belhard.lesson.dao;

import com.belhard.lesson.object.Person;
import com.belhard.lesson.servise.Servise;

import java.sql.Date;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class PaymentsBaseDao implements PaymentsDao {
    public Connection c;
    public Statement statement;
    private ArrayList<String> communal;

    public PaymentsBaseDao(String data, Servise servise) throws Exception {
        this.communal = new PaymentsBaseDao(0, null, data, 0, 0, servise).communal;

    }


    public PaymentsBaseDao(int id, Person payer, String data, int check, int payments, Servise servise) throws Exception {
        communal = new ArrayList<>();
        Date date = Date.valueOf(data);
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
                statement.executeUpdate(setPayments(id, payer, date.toLocalDate(), check, payments));
                c.commit();
                break;
            }
            case UPDATE -> {
                statement.executeUpdate(updatePayments(id, payer, check, payments));
                c.commit();
                break;
            }
            case GET, GET_CREDITOR, GET_DEBTOR -> {
                ResultSet rs = null;
                if (servise == Servise.GET) {
                    rs = statement.executeQuery(getPayments(date.toLocalDate()));
                }
                if (servise == Servise.GET_CREDITOR) {
                    rs = statement.executeQuery(getCreditor(date.toLocalDate()));
                }
                if (servise == Servise.GET_DEBTOR) {
                    rs = statement.executeQuery(getCreditor(date.toLocalDate()));
                }

                while (rs.next()) {
                    String num_id = String.valueOf(rs.getInt("num_id"));
                    String num_data = String.valueOf(rs.getDate("data_payments"));
                    String num_check = String.valueOf(rs.getInt("check_"));
                    String num_payments = String.valueOf(rs.getInt("payments"));
                    String num_name = rs.getString("name");
                    String num_family_name = rs.getString("family_name");
                    String num_address = rs.getString("address");
                    String indebtedness = String.valueOf(rs.getInt("check_") - rs.getInt("payments"));
                    communal.add(num_id + ", ");
                    communal.add(num_data + ", ");
                    communal.add(num_check + ", ");
                    communal.add(num_payments + ", ");
                    communal.add(num_name + ", ");
                    communal.add(num_family_name + ", ");
                    communal.add(num_address + ", ");
                    if (servise == Servise.GET_CREDITOR) {
                        communal.add("задолженность " + indebtedness + ".");
                    }
                    if (servise == Servise.GET_DEBTOR) {
                        communal.add("переплата " + indebtedness + ".");
                    }
                }
                rs.close();

                break;
            }
        }
        statement.close();
        c.close();


    }


    @Override
    public String setPayments(int id, Person payer, LocalDate date, int check, int payments) throws ParseException {
        String qs = "insert into PaymentsTable (num_id, data_payments, check_, payments)" + "values(" + id + ",'" + date + "'," +
                check + "," + payments + ");" +
                "insert into PayersTable(num_id, name, family_name, address)" + "values(" + id + ",'" +
                payer.getName() + "','" + payer.getFamily_name() + "','" + payer.getAddress().toString() + "');";
        return qs;
    }

    @Override
    public String updatePayments(int id, Person payer, int check, int payments) throws ParseException {

        String qs = "update PaymentsTable set check_=" + check + ", payments=" + payments + "where num_id=" + id + ";"
                + "update PayersTable set name='" + payer.getName() + "', family_name='" + payer.getFamily_name() + "', address='" + payer.getAddress().toString() + "'where num_id=" + id + ";";

        return qs;

    }

    @Override
    public String getPayments(LocalDate date) {
        String qs = "select * from PaymentsTable, PayersTable where check_ = payments and PaymentsTable.num_id=PayersTable.num_id and data_payments ='" + date + "';";
        return qs;
    }

    @Override
    public String getDebtor(LocalDate date) {
        String qs = "select * from PaymentsTable, PayersTable where check_ < payments and PaymentsTable.num_id=PayersTable.num_id and data_payments ='" + date + "';";
        return qs;
    }

    @Override
    public String getCreditor(LocalDate date) {
        String qs = "select * from PaymentsTable, PayersTable where check_ > payments and PaymentsTable.num_id=PayersTable.num_id and data_payments ='" + date + "';";
        return qs;
    }

    @Override
    public ArrayList<String> getCommunal() {
        return communal;
    }


}
