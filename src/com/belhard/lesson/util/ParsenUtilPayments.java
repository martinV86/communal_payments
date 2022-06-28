package com.belhard.lesson.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParsenUtilPayments {
    public ParsenUtilPayments(String fail, String state, ArrayList<String> communal) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(fail);
            fileWriter.write(state);
            for (String x : communal) {
                fileWriter.write(x);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {

            }
        }

    }
}
