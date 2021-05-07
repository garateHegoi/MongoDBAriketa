/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import view.JavaFX;

/**
 *
 * @author Hegoi
 */
public class Main2 {
    public static SessionFactory sf = new Configuration().configure().buildSessionFactory();
        public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(JavaFX.class);
            }
        }.start();
    }
    public static void datuaGordeMariaDB() {
        Scanner sn = new Scanner(System.in);
        int idBerria = 0;
        String izenBerria;

        System.out.println("Sartu artista berriaren izena: ");
        izenBerria = sn.next();

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        saioa.save(izenBerria);
        saioa.getTransaction().commit();
        saioa.close();

    }

    public static void datuakIkusiMariaDB() {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        List result = saioa.createQuery("from books").list();
        for (Book a : (List<Book>) result) {
            System.out.println(a);
        }
    }

    public static void datuakKenduMariaDB() {
        Scanner sn = new Scanner(System.in);
        int idArtistaEzabatu;
        System.out.println("Sartu ezabatu nahi duzun artistaren ID-a: ");
        idArtistaEzabatu = sn.nextInt();
        Session saoia = sf.openSession();
        saoia.beginTransaction();

        Book a = saoia.find(Book.class, idArtistaEzabatu);
        saoia.remove(a);
        saoia.getTransaction().commit();
        System.out.println("Artista ondo ezabatuta!!!");
        saoia.close();
    }

    public static void JsonToCsv(File jsonFile, File csvFile) throws IOException {
        JsonNode jsonTree = new ObjectMapper().readTree(jsonFile);

        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
        JsonNode firstObject = jsonTree.elements().next();
        firstObject.fieldNames().forEachRemaining(fieldName -> {
            csvSchemaBuilder.addColumn(fieldName);
        });
        CsvSchema csvSchema = csvSchemaBuilder
                .build()
                .withHeader();

        CsvMapper csvMapper = new CsvMapper();
        csvMapper.writerFor(JsonNode.class)
                .with(csvSchema)
                .writeValue(csvFile, jsonTree);
    }
}
