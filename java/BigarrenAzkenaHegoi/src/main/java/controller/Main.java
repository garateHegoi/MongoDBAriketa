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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import model.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import view.JavaFX;

/**
 *
 * @author Hegoi
 */

public class Main {
    
    //Mongo konfigurazioa

    static CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    static MongoClientSettings settings = MongoClientSettings.builder().codecRegistry(pojoCodecRegistry).build();
    static MongoClient mongoClient = MongoClients.create(settings);
    static MongoDatabase database = mongoClient.getDatabase("bigarrenAzkena");
    static MongoCollection<Book> collection = database.getCollection("books", Book.class);
    
    //aplikazioa hasi

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                javafx.application.Application.launch(JavaFX.class);
            }
        }.start();
    }
    //Mongorekin dena irakurri
    public static void irakurriDena() {

        try ( MongoCursor<Book> cur = collection.find().iterator()) {

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                    "Id", "Author", "Country", "Genres", "ImageLink", "Language", "Link", "Pages", "Title", "Year");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (cur.hasNext()) {

                Book book = cur.next();
                System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                        book.getNum(), book.getAuthor(), book.getCountry(), book.getGenres(), book.getImageLink(), book.getLanguage(), book.getLink(), book.getPages(), book.getTitle(), book.getYear());
            }
        }

    }
    //kategoriaz bilatu
    public static void irakurriGuztiaKategoriatik() {
        String genre = "";
        System.out.println("Which genre do you want to display? (Write the corresponding number)");
        System.out.println("1) Fantasy");
        System.out.println("2) Historical");
        System.out.println("3) Filosofical");
        System.out.println("4) Mithological");
        System.out.println("5) Biographical");
        System.out.println("6) Mistery");
        System.out.println("7) Poetry");
        System.out.println("8) Fiction");
        System.out.println("9) Novel");
        System.out.println("10) Romance");
        System.out.println("11) Horror");
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        switch (choice) {
            case "1":
                genre = "Fantasy";
                break;
            case "2":
                genre = "Historical";
                break;
            case "3":
                genre = "Filosofical";
                break;
            case "4":
                genre = "Mithological";
                break;
            case "5":
                genre = "Biographical";
                break;
            case "6":
                genre = "Mistery";
                break;
            case "7":
                genre = "Poetry";
                break;
            case "8":
                genre = "Fiction";
                break;
            case "9":
                genre = "Novel";
                break;
            case "10":
                genre = "Romance";
                break;
            case "11":
                genre = "Horror";
                break;
            default:
                System.out.println("That's not a valid choice!");
        }

        if (!genre.equals("")) {

            try ( MongoCursor<Book> cur = collection.find(all("genres", genre)).iterator()) {

                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                        "Id", "Author", "Country", "Genres", "ImageLink", "Language", "Link", "Pages", "Title", "Year");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                while (cur.hasNext()) {
                    Book book = cur.next();
                    if (book.getGenres().toString().contains(genre)) {
                        System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                                book.getNum(), book.getAuthor(), book.getCountry(), book.getGenres(), book.getImageLink(), book.getLanguage(), book.getLink(), book.getPages(), book.getTitle(), book.getYear());
                    }
                }
            }
        }
    }
    //liburu bat sortu
    public static void liburuakSortu() {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Asign values to these fields:");
        System.out.println("---------------------");
        Book book = new Book();
        Boolean idrRight = false, pagesRight = false, yearRight = false;
        int id = 0, pages = 0, year = 0;
        List<String> genres = new ArrayList<>();
        String trash;
        try {
            while (!idrRight) {
                System.out.println("Id:");
                try {
                    id = in.nextInt();
                    idrRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("The id must be a number!");
                    trash = in.next();
                }
            }
            book.setNum(id);
            System.out.println("Title:");
            book.setTitle(in.next());
            System.out.println("Author:");
            book.setAuthor(in.next());
            System.out.println("Genres (separate by coma):");
            genres.addAll(Arrays.asList(in.next().split(",")));
            book.setGenres(genres);
            System.out.println("Country:");
            book.setCountry(in.next());
            System.out.println("Image Link:");
            book.setImageLink(in.next());
            System.out.println("Language:");
            book.setLanguage(in.next());
            System.out.println("Link:");
            book.setLink(in.next());
            while (!pagesRight) {
                System.out.println("Page amount:");
                try {
                    pages = in.nextInt();
                    pagesRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("The page amount must be a number!");
                    trash = in.next();
                }
            }
            book.setPages(pages);
            while (!yearRight) {
                System.out.println("Year:");
                try {
                    year = in.nextInt();
                    yearRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("The year must be a number!");
                    trash = in.next();
                }
            }
            book.setYear(year);

            collection.insertOne(book);
        } catch (MongoWriteException e) {
            System.out.println("Thit book already exists");
        }
    }
    //liburu bat aldatu
    public static void updateBook() {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Asign values to these fields:");
        System.out.println("---------------------");
        Book book = new Book();
        Boolean idRight = false, pagesRight = false, yearRight = false;
        int id = 0, pages = 0, year = 0;
        List<String> genres = new ArrayList<>();
        String trash;
        try {
            while (!idRight) {
                System.out.println("Id:");
                try {
                    id = in.nextInt();
                    idRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("The id must be a number!");
                    trash = in.next();
                }
            }
            book.setNum(id);
            System.out.println("Title:");
            book.setTitle(in.next());
            System.out.println("Author:");
            book.setAuthor(in.next());
            System.out.println("Genres (separate by coma):");
            genres.addAll(Arrays.asList(in.next().split(",")));
            book.setGenres(genres);
            System.out.println("Country:");
            book.setCountry(in.next());
            System.out.println("Image Link:");
            book.setImageLink(in.next());
            System.out.println("Language:");
            book.setLanguage(in.next());
            System.out.println("Link:");
            book.setLink(in.next());
            while (!pagesRight) {
                System.out.println("Page amount:");
                try {
                    pages = in.nextInt();
                    pagesRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("The page amount must be a number!");
                    trash = in.next();
                }
            }
            book.setPages(pages);
            while (!yearRight) {
                System.out.println("Year:");
                try {
                    year = in.nextInt();
                    yearRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("The year must be a number!");
                    trash = in.next();
                }
            }
            book.setYear(year);

            collection.replaceOne(eq("num", id), book);
        } catch (MongoCommandException e) {
            System.out.println("This book already exists");
        }
    }
    //liburu bat ezabatu
    public static void deleteBook() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Enter the books id:");
            int id = in.nextInt();

            collection.deleteOne(Filters.eq("num", id));
            System.out.println("The book has been properly deleted");

        } catch (MongoCommandException e) {
            System.out.println("The book you tried to delete does not exist");
        }
    }
}