package com.example.moksleivis.knygalaboras;

/**
 * Created by arvyd on 1/21/2018.
 */

public class Knyga {
    private int id;
    private String name;
    private String release_year;
    private String author;
    private String genre; //check box
    private String rarity;//choice box
    private int pages;
    private String cover; // radio button hard/soft cover
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getRelease_year() {
        return release_year;
    }
    public void setRelease_year(String release_date) {
        this.release_year = release_date;
    }
    public int getPages() {
        return pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getRarity() {
        return rarity;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public Knyga(int id, String name, String release_year,  String author, String genre,
                 String rarity,int pages, String cover) {
        super();
        this.id = id;
        this.name = name;

        this.release_year = release_year;
        this.pages = pages;
        this.author = author;
        this.genre = genre;
        this.rarity = rarity;
        this.cover = cover;
    }
    public Knyga(String name,  String release_year, String author, String genre, String rarity, int pages,
                 String cover) {
        super();
        this.name = name;

        this.release_year = release_year;
        this.pages = pages;
        this.author = author;
        this.genre = genre;
        this.rarity = rarity;
        this.cover = cover;
    }
    public Knyga() {
        super();
    }
    @Override
    public String toString() {
        return "Knyga [id=" + id + ", name=" + name + ", release_year=" + release_year + ", author=" + author
                + ", genre=" + genre + ", rarity=" + rarity + ", pages=" + pages + ", cover=" + cover + "]";
    }

}




