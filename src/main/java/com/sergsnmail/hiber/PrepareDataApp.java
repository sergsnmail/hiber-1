package com.sergsnmail.hiber;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrepareDataApp {
    public static void forcePrepareData() {
        SessionFactory factory = new Configuration()
                .configure("config/mysql/hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
        try {
            String queryMultiState = new String(Files.readAllBytes(Paths.get("full.sql")));
            session = factory.getCurrentSession();
            session.beginTransaction();
            for (String queryStr : getStatements(queryMultiState, ";\r\n")) {
                session.createNativeQuery(queryStr).executeUpdate();
            }
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }

    private static List<String> getStatements(String sqlString, String delimeter){
        List<String> statements = new ArrayList<>();
        if (!sqlString.isEmpty()){
            String[] tmpStr = sqlString.split(delimeter);
            statements = Arrays.stream(tmpStr).collect(Collectors.toList());
        }
        return statements;
    }
}
