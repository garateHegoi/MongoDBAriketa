package view;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import controller.*;
import model.*;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

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
    
    public static void readAll() {
    
        try ( MongoCursor<Book> cur = collection.find().iterator()) {

            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%20s | %20s | %20s | %20s | %40s | %20s | %20s | %20s | %20s | %20s%n",
                    "Title", "Isbn", "Page Count", "Published Date", "Thhumbnail Url", "Short Description", "Status", "Authors", "Categories");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            while (cur.hasNext()) {

                Book book = cur.next();
                System.out.printf("%20s | %20s | %20s | %20s | %40s | %20s | %20s | %20s | %20s | %20s%n",
                        book.getTitle(), book.getIsbn(), book.getPageCount(), book.getPublishedDate(), book.getThumbnailUrl(), book.getShortDescription(), book.isStatus(), book.getAuthors(), book.getCategories());
            }
        }
    }
}
