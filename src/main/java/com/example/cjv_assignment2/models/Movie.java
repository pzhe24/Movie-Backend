package com.example.cjv_assignment2.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
// says that there will be a document called movies in our collection.
public class Movie {

//    by adding the Id annotation, it means that we will have a collection that will contain the following keys
    @Id
    private String id;
    private String title;
    private String year;
    private String description;
    private String genres;
    private String runtime;
    private String poster;
    private String bigposter;
    private String rentPrice;
    private String buyPrice;
    private String isFeatured;
    private String type;

    public Movie(){

    }

    public Movie(String id, String title, String year, String description, String genres, String runtime, String poster, String bigposter, String rentPrice, String buyPrice, String isFeatured, String type) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.description = description;
        this.genres = genres;
        this.runtime = runtime;
        this.poster = poster;
        this.bigposter = bigposter;
        this.rentPrice = rentPrice;
        this.buyPrice = buyPrice;
        this.isFeatured = isFeatured;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String length) {
        this.runtime= length;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBigposter() {
        return bigposter;
    }

    public void setBigposter(String bigposter) {
        this.bigposter = bigposter;
    }

    public String getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(String rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(String buyPrice) {
        this.buyPrice = buyPrice;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                ", genres='" + genres + '\'' +
                ", runtime='" + runtime + '\'' +
                ", poster='" + poster + '\'' +
                ", bigposter='" + bigposter + '\'' +
                ", rentPrice='" + rentPrice + '\'' +
                ", buyPrice='" + buyPrice + '\'' +
                ", isFeatured='" + isFeatured + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
