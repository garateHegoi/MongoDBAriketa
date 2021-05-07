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

    /**
     *id-a deklaratu
     */
    public ObjectId _id;

    /**
     *zenbakia deklaratu
     */
    public int num;

    /**
     *autorea deklaratu
     */
    public String author;

    /**
     *probintzia deklaratu
     */
    public String country;

    /**
     *generoak deklaratu
     */
    public List<String> genres;

    /**
     *imagina deklaratu
     */
    public String imageLink;

    /**
     *hizkuntza deklaratu
     */
    public String language;

    /**
     *linka deklaratu
     */
    public String link;

    /**
     *orri kopurua deklaratu
     */
    public int pages;

    /**
     *titulua deklaratu
     */
    public String title;

    /**
     *urtea deklaratu
     */
    public int year;
    
    //Konstruktore utza

    /**
     *konstruktore utza
     */
    public Book() {
    }

    /**
     *konstruktorea hasieratu
     */
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

    /**
     *
     * Id aren getterra
     */
    public ObjectId getId() {
        return _id;
    }

    /**
     *
     * zenbakiaren getterra
     */
    public int getNum() {
        return num;
    }

    /**
     *
     * autorearen getterra
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * herrialdearen getterra
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * generoen getterra
     */
    public List<String> getGenres() {
        return genres;
    }

    /**
     *
     * imaginaren getterra
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     *
     * hizkuntzaren getterra
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * linkaren getterra
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * orrien getterra
     */
    public int getPages() {
        return pages;
    }

    /**
     *
     * tituluaren getterra
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * urtearen getterra
     */
    public int getYear() {
        return year;
    }

    /**
     *
     * id-aren setterra
     */
    public void setId(ObjectId _id) {
        this._id = _id;
    }

    /**
     *
     * zenbakiaren setterra
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     *
     * autorearen setterra
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * probintziaren setterra
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * generoaren setterra
     */
    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    /**
     *
     * imaginaren setterra
     */
    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    /**
     *
     * hinkuntzaren setterra
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * linkaren setterra
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * orriaren setterra
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     *
     * tituluaren setterra
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * urtearen setterra
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     *
     * toString-a
     */
    @Override
    public String toString() {
        return "Book{" + "_id=" + _id + ", num=" + num + ", author=" + author + ", country=" + country + ", genres=" + genres + ", imageLink=" + imageLink + ", language=" + language + ", link=" + link + ", pages=" + pages + ", title=" + title + ", year=" + year + '}';
    }
}
