package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
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
import java.io.File;
import java.io.IOException;
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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import view.JavaFX;
import org.bson.types.ObjectId;

/**
 *
 * @author Hegoi
 */
public class Main {

    //MariaDB konfigurazioa
    public static SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

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

        try (MongoCursor<Book> cur = collection.find().iterator()) {

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                    "Id", "Autorea", "Probintzia", "Generoa", "ImageLink", "Hizkuntza", "Link", "Orriak", "Titulua", "Urtea");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (cur.hasNext()) {

                Book book = cur.next();
                System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                        book.getNum(), book.getAuthor(), book.getCountry(), book.getGenres(), book.getImageLink(), book.getLanguage(), book.getLink(), book.getPages(), book.getTitle(), book.getYear());
            }
            System.out.println("");
            System.out.println("");
            System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");
        }

    }

    //kategoriaz bilatu
    public static void irakurriGuztiaKategoriatik() {
        String genre = "";
        System.out.println("Zein generoa ikusi nahi duzu? (Idatzi dagoeneko zenbakia)");
        System.out.println("1) Fantasia");
        System.out.println("2) Historikoa");
        System.out.println("3) Filosofikoa");
        System.out.println("4) Mitologikoa");
        System.out.println("5) Biografikoa");
        System.out.println("6) Misterioko");
        System.out.println("7) Poesia");
        System.out.println("8) Fikzioa");
        System.out.println("9) Nobela");
        System.out.println("10) Romantikoa");
        System.out.println("11) Horrorea");
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
                genre = "Filosophical";
                break;
            case "4":
                genre = "Mitological";
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
                System.out.println("Opzio hori okerra da!");
        }

        if (!genre.equals("")) {

            try (MongoCursor<Book> cur = collection.find(all("genres", genre)).iterator()) {

                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                        "Id", "Autorea", "Probintzia", "Generoa", "ImageLink", "Hizkuntza", "Link", "Orriak", "Titulua", "Urtea");
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                while (cur.hasNext()) {
                    Book book = cur.next();
                    if (book.getGenres().toString().contains(genre)) {
                        System.out.printf("%5.5s | %20.20s | %30.30s | %20.20s | %40.40s | %20.20s | %50.50s | %10.10s | %30.30s | %10.10s%n",
                                book.getNum(), book.getAuthor(), book.getCountry(), book.getGenres(), book.getImageLink(), book.getLanguage(), book.getLink(), book.getPages(), book.getTitle(), book.getYear());
                    }
                }
                System.out.println("");
                System.out.println("");
                System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");
            }
        }
    }

    //liburu bat sortu
    public static void liburuakSortu() {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Baloreak asignatu:");
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
                    System.out.println("Id-a zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setNum(id);
            System.out.println("Titulua:");
            book.setTitle(in.next());
            System.out.println("Autorea:");
            book.setAuthor(in.next());
            System.out.println("Generoa (komekin separatu):");
            genres.addAll(Arrays.asList(in.next().split(",")));
            book.setGenres(genres);
            System.out.println("Probintzia:");
            book.setCountry(in.next());
            System.out.println("Image Link:");
            book.setImageLink(in.next());
            System.out.println("Hizkuntza:");
            book.setLanguage(in.next());
            System.out.println("Link:");
            book.setLink(in.next());
            while (!pagesRight) {
                System.out.println("Orri kopurua:");
                try {
                    pages = in.nextInt();
                    pagesRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Orri kopurua zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setPages(pages);
            while (!yearRight) {
                System.out.println("Urtea:");
                try {
                    year = in.nextInt();
                    yearRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Urtea zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setYear(year);

            collection.insertOne(book);
            System.out.println("");
            System.out.println("");
            System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");
        } catch (MongoWriteException e) {
            System.out.println("Liburu hori existitzen da");
        }
    }

    //liburu bat aldatu
    public static void updateBook() {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Asignatu dagokion baloreak:");
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
                    System.out.println("Id-a zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setNum(id);
            System.out.println("Titulua:");
            book.setTitle(in.next());
            System.out.println("Autorea:");
            book.setAuthor(in.next());
            System.out.println("Generoa (komekin separatu):");
            genres.addAll(Arrays.asList(in.next().split(",")));
            book.setGenres(genres);
            System.out.println("Probintzia:");
            book.setCountry(in.next());
            System.out.println("Image Link:");
            book.setImageLink(in.next());
            System.out.println("Hizkuntza:");
            book.setLanguage(in.next());
            System.out.println("Link:");
            book.setLink(in.next());
            while (!pagesRight) {
                System.out.println("Orri kopurua:");
                try {
                    pages = in.nextInt();
                    pagesRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Orri kopurua zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setPages(pages);
            while (!yearRight) {
                System.out.println("Urtea:");
                try {
                    year = in.nextInt();
                    yearRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Urtea zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            book.setYear(year);

            collection.replaceOne(eq("num", id), book);
            System.out.println("");
            System.out.println("");
            System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");
        } catch (MongoCommandException e) {
            System.out.println("Datuak gaizki sartu duzu");
        }
    }

    //liburu bat ezabatu
    public static void deleteBook() {
        Scanner in = new Scanner(System.in);
        try {
            System.out.println("Liburuaren id sartu:");
            int id = in.nextInt();

            collection.deleteOne(Filters.eq("num", id));
            System.out.println("Liburua ondo ezabatu egin da");
            System.out.println("");
            System.out.println("");
            System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");

        } catch (MongoCommandException e) {
            System.out.println("Nahi zenuen liburua ez da existitzen");
        }
    }

    public static void datuaGordeMariaDB() {
        Scanner in = new Scanner(System.in);
        System.out.println("---------------------");
        System.out.println("Baloreak asignatu:");
        System.out.println("---------------------");
        Session saioa = sf.openSession();
        saioa.beginTransaction();
        Book newbook = new Book();
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
                    System.out.println("Id-a zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            newbook.setNum(id);
            System.out.println("Titulua:");
            newbook.setTitle(in.next());
            System.out.println("Autorea:");
            newbook.setAuthor(in.next());
            System.out.println("Generoa (komekin separatu):");
            genres.addAll(Arrays.asList(in.next().split(",")));
            newbook.setGenres(genres);
            System.out.println("Probintzia:");
            newbook.setCountry(in.next());
            System.out.println("Image Link:");
            newbook.setImageLink(in.next());
            System.out.println("Hizkuntza:");
            newbook.setLanguage(in.next());
            System.out.println("Link:");
            newbook.setLink(in.next());
            System.out.println("ISBN");
            String idberria;
            idberria = in.next();
            if (ObjectId.isValid(idberria)) {
                ObjectId objectId = new ObjectId(idberria);
                System.out.println(objectId);
                newbook.setId(objectId);
            } else {
                System.out.println("Invalid id");
            }
            while (!pagesRight) {
                System.out.println("Orri kopurua:");
                try {
                    pages = in.nextInt();
                    pagesRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Orri kopurua zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            newbook.setPages(pages);
            while (!yearRight) {
                System.out.println("Urtea:");
                try {
                    year = in.nextInt();
                    yearRight = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Urtea zenbaki bat izan behar da!");
                    trash = in.next();
                }
            }
            newbook.setYear(year);

            System.out.println("");
            System.out.println("");
            System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");
            saioa.save(newbook);
            saioa.getTransaction().commit();
            saioa.close();
        } catch (MongoWriteException e) {
            System.out.println("Liburu hori existitzen da");
        }
    }

    public static void datuakIkusiMariaDB() {

        Session saioa = sf.openSession();
        saioa.beginTransaction();
        Query query = saioa.createQuery("from Book");
        List<Book> books = query.list();
        for (Book book : books) {
            System.out.println("Id: " + book.getNum() + ", Autorea: " + book.getAuthor() + ", Probintzia: " + book.getCountry() + ", Generoa: " + book.getGenres() + ", Imagina: " + book.getImageLink() + ", Hizkuntza: " + book.getLanguage() + ", Linka: " + book.getLink() + ", Orriak: " + book.getPages() + ", Titulua: " + book.getTitle() + ", Urtea: " + book.getYear());
        }
        saioa.getTransaction().commit();
        saioa.close();
        System.out.println("");
        System.out.println("");
        System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");
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
        System.out.println("");
        System.out.println("");
        System.out.println("Operazioa ondo egin da. Menuarekin jarraitu.");
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
