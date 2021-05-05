/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author Hegoi
 */
public class Book {

    public ObjectId _id;
    public String title;
    public int isbn;
    public int pageCount;
    public Date publishedDate;
    public String thumbnailUrl;
    public String shortDescription;
    public boolean status;
    public List<String> Authors;
    public List<String> Categories;

    public Book() {
    }

    public Book(ObjectId _id, String title, int isbn, int pageCount, Date publishedDate, String thumbnailUrl, String shortDescription, boolean status, List<String> Authors, List<String> Categories) {
        this._id = _id;
        this.title = title;
        this.isbn = isbn;
        this.pageCount = pageCount;
        this.publishedDate = publishedDate;
        this.thumbnailUrl = thumbnailUrl;
        this.shortDescription = shortDescription;
        this.status = status;
        this.Authors = Authors;
        this.Categories = Categories;
    }

    public ObjectId getId() {
        return _id;
    }

    public void setId(ObjectId _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getAuthors() {
        return Authors;
    }

    public void setAuthors(List<String> Authors) {
        this.Authors = Authors;
    }

    public List<String> getCategories() {
        return Categories;
    }

    public void setCategories(List<String> Categories) {
        this.Categories = Categories;
    }

    @Override
    public String toString() {
        return "Book{" + "_id=" + _id + ", title=" + title + ", isbn=" + isbn + ", pageCount=" + pageCount + ", publishedDate=" + publishedDate + ", thumbnailUrl=" + thumbnailUrl + ", shortDescription=" + shortDescription + ", status=" + status + ", Authors=" + Authors + ", Categories=" + Categories + '}';
    }

}
