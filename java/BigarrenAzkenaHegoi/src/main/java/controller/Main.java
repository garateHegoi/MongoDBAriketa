package controller;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCommandException;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.all;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import view.JavaFX;

public class Main {

    static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    static MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();
    static MongoClient mongoClient = MongoClients.create(settings);
    static MongoDatabase database = mongoClient.getDatabase("bigarrenAzkena");
    static MongoCollection<Book> collection = database.getCollection("books", Book.class);

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(JavaFX.class);
            }
        }.start();
    }

    public static void irakurriDena() {

        try (MongoCursor<Book> cur = collection.find().iterator()) {

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%20d | %20s | %20s | %20s | %20s | %40s | %20s | %20s | %20s | %20s | %20s%n",
                    "_id", "Title", "Isbn", "Page Count", "Published Date", "Thhumbnail Url", "Short Description", "Status", "Authors", "Categories");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (cur.hasNext()) {

                Book book = cur.next();
                System.out.printf("%20d | %20s | %20s | %20s | %20s | %40s | %20s | %20s | %20s | %20s | %20s%n",
                        book.getId(), book.getTitle(), book.getIsbn(), book.getPageCount(), book.getPublishedDate(), book.getThumbnailUrl(), book.getShortDescription(), book.isStatus(), book.getAuthors(), book.getCategories());
            }
        }
    }

    public static void irakurriGuztiaKategoriatik() {
        String kategoria = "";
        System.out.println("Zeinen kategoria ikusi nahi duzu? (idatzi dagokion zenbakia)");
        System.out.println("1) Open Source");
        System.out.println("2) Mobile");
        System.out.println("3) Java");
        System.out.println("4) Software Engineering");
        System.out.println("5) Internet");
        System.out.println("6) Miscellaneous");
        System.out.println("7) Microsoft .NET");
        System.out.println("8) Databases");
        System.out.println("9) Client-Server");
        System.out.println("10) Computer Graphics");
        System.out.println("11) Web Development");
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        switch (choice) {
            case "1":
                kategoria = "Open Source";
                break;
            case "2":
                kategoria = "Mobile";
                break;
            case "3":
                kategoria = "Java";
                break;
            case "4":
                kategoria = "Software Engineering";
                break;
            case "5":
                kategoria = "Internet";
                break;
            case "6":
                kategoria = "Miscellaneous";
                break;
            case "7":
                kategoria = "Microsoft .NET";
                break;
            case "8":
                kategoria = "Databases";
                break;
            case "9":
                kategoria = "Client-Server";
                break;
            case "10":
                kategoria = "Computer Graphics";
                break;
            case "11":
                kategoria = "Web Development";
                break;
            default:
                System.out.println("Sartu duzun aukera okerra da!");
        }

        if (!kategoria.equals("")) {

            try (MongoCursor<Book> cur = collection.find(all("kategoria", kategoria)).iterator()) {

                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%20d | %20s | %20d | %20s | %20s | %40s | %20s | %20s | %20s | %20s | %20s%n",
                        "_id", "Title", "Isbn", "Page Count", "Published Date", "Thhumbnail Url", "Short Description", "Status", "Authors", "Categories");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                while (cur.hasNext()) {

                    Book book = cur.next();
                    if (book.getCategories().toString().contains(kategoria)) {
                        System.out.printf("%20d | %5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                                book.getId(), book.getTitle(), book.getIsbn(), book.getPageCount(), book.getPublishedDate(), book.getThumbnailUrl(), book.getShortDescription(), book.isStatus(), book.getAuthors(), book.getCategories());
                    }
                }
            }
        }
    }

    public static void liburuakSortu() {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Baloreak sartu:");
        System.out.println("---------------------");
        Book book = new Book();
        Boolean isbnOndo = false, orriakOndo = false, dataOndo = false;
        int isbn = 0, orriak = 0;
        Date data;
        List<String> kategoria = new ArrayList<>();
        List<String> autoreak = new ArrayList<>();
        String trash;
        try {
            while (!isbnOndo) {
                System.out.println("Isbn:");
                try {
                    isbn = in.nextInt();
                    isbnOndo = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Sartu duzun isbn ez da zenbaki bat!");
                    trash = in.next();
                }
            }
            book.setIsbn(isbn);
            System.out.println("Titulua:");
            book.setTitle(in.next());
            System.out.println("Autoreak (separatu komarekin):");
            autoreak.addAll(Arrays.asList(in.next().split(",")));
            book.setAuthors(autoreak);
            System.out.println("Kategoria (separatu komarekin):");
            kategoria.addAll(Arrays.asList(in.next().split(",")));
            book.setCategories(kategoria);
            System.out.println("Deskripzioa:");
            book.setShortDescription(in.next());
            System.out.println("Imagina Linka:");
            book.setThumbnailUrl(in.next());
            System.out.println("Aktibo estatua (true edo false):");
            book.setStatus(in.nextBoolean());

            while (!orriakOndo) {
                System.out.println("Orri kopurua:");
                try {
                    orriak = in.nextInt();
                    orriakOndo = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Orri kopurua zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setPageCount(orriak);

            collection.insertOne(book);
        } catch (MongoWriteException e) {
            System.out.println("Liburu hau dagoeko existitzen da");
        }
    }

    public static void updateBook() {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Baloreak sartu:");
        System.out.println("---------------------");
        Book book = new Book();
        Boolean isbnOndo = false, orriakOndo = false, dataOndo = false;
        int isbn = 0, orriak = 0;
        Date data;
        List<String> kategoria = new ArrayList<>();
        List<String> autoreak = new ArrayList<>();
        String trash;
        try {
            while (!isbnOndo) {
                System.out.println("Isbn:");
                try {
                    isbn = in.nextInt();
                    isbnOndo = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Sartu duzun isbn ez da zenbaki bat!");
                    trash = in.next();
                }
            }
            book.setIsbn(isbn);
            System.out.println("Titulua:");
            book.setTitle(in.next());
            System.out.println("Autoreak (separatu komarekin):");
            autoreak.addAll(Arrays.asList(in.next().split(",")));
            book.setAuthors(autoreak);
            System.out.println("Kategoria (separatu komarekin):");
            kategoria.addAll(Arrays.asList(in.next().split(",")));
            book.setCategories(kategoria);
            System.out.println("Deskripzioa:");
            book.setShortDescription(in.next());
            System.out.println("Imagina Linka:");
            book.setThumbnailUrl(in.next());
            System.out.println("Aktibo estatua (true edo false):");
            book.setStatus(in.nextBoolean());

            while (!orriakOndo) {
                System.out.println("Orri kopurua:");
                try {
                    orriak = in.nextInt();
                    orriakOndo = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Orri kopurua zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setPageCount(orriak);

            collection.replaceOne(eq("num", isbn), book);
        } catch (MongoCommandException e) {
            System.out.println("Liburu hau dagoeko existitzen da");
        }
    }

    public static void deleteBook() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Liburuaren isbn-a sartu:");
            int isbn = in.nextInt();

            collection.deleteOne(Filters.eq("isbn", isbn));
            System.out.println("Liburua ondo ezabatu da");

        } catch (MongoCommandException e) {
            System.out.println("Sartu duzun isbn-a ez da existitzen");
        }
    }
}
