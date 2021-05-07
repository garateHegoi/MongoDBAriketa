/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Hegoi
 */
public class Book {

    //Bariableak declaratu
    public ObjectId _id;
    public int num;
    public String author;
    public String country;
    public List<String> genres;
    public String imageLink;
    public String language;
    public String link;
    public int pages;
    public String title;
    public int year;
    
    //Konstruktore utza
    public Book() {
    }
    //Konstruktorea hasieratu
    public Book(ObjectId _id, int num, String author, String country, List<String> genres, String imageLink, String language, String link, int pages, String title, int year) {
        this._id = _id;
        this.num = num;
        this.author = author;
        this.country = country;
        this.genres = genres;
        this.imageLink = imageLink;
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }

    public Book(int num, String author, String country, List<String> genres, String imageLink, String language, String link, int pages, String title, int year) {
        this.num = num;
        this.author = author;
        this.country = country;
        this.genres = genres;
        this.imageLink = imageLink;
        this.language = language;
        this.link = link;
        this.pages = pages;
        this.title = title;
        this.year = year;
    }
    //Id aren getterra
    public ObjectId getId() {
        return _id;
    }
    //Zenbakiaren getterra
    public int getNum() {
        return num;
    }
    //Autoreen getterra
    public String getAuthor() {
        return author;
    }
    //Herrialdearen getterra 
    public String getCountry() {
        return country;
    }
    //Generoen getterra
    public List<String> getGenres() {
        return genres;
    }
    //imaginaren getterra
    public String getImageLink() {
        return imageLink;
    }
    //hizkuntzzaren getterra
    public String getLanguage() {
        return language;
    }
    // linkarren getterra
    public String getLink() {
        return link;
    }
    //orrien getterra
    public int getPages() {
        return pages;
    }
    //tituluaren getterra
    public String getTitle() {
        return title;
    }
    //urtea getterra
    public int getYear() {
        return year;
    }
    //id setterra
    public void setId(ObjectId _id) {
        this._id = _id;
    }
    //zenbakiaren setterra
    public void setNum(int num) {
        this.num = num;
    }
    //autorearen setterra
    public void setAuthor(String author) {
        this.author = author;
    }
    //probintzia setterra
    public void setCountry(String country) {
        this.country = country;
    }
    //generoen setterra
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }
    //imgaina getterra
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
    //hizkuntza setterra
    public void setLanguage(String language) {
        this.language = language;
    }
    //linka setterra
    public void setLink(String link) {
        this.link = link;
    }
    //orri setterra
    public void setPages(int pages) {
        this.pages = pages;
    }
    //tituluen setterra
    public void setTitle(String title) {
        this.title = title;
    }
    //urteen setterra
    public void setYear(int year) {
        this.year = year;
    }
    //tostringa
    @Override
    public String toString() {
        return "Book{" + "_id=" + _id + ", num=" + num + ", author=" + author + ", country=" + country + ", genres=" + genres + ", imageLink=" + imageLink + ", language=" + language + ", link=" + link + ", pages=" + pages + ", title=" + title + ", year=" + year + '}';
    }
}
