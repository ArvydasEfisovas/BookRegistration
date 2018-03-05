package com.example.moksleivis.knygalaboras;

/**
 * Created by arvyd on 1/21/2018.
 */
public class Knyga {
    private int id;
    private String name;
    private String release_year;
    private String author;
    private String genre;
    private String rarity;
    private int pages;
    private String cover;
    private int check1;
    private int check2;
    private int check3;
    private int check4;

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

    public int getCheck1() {
        return check1;
    }

    public void setCheck1(int check1) {
        this.check1 = check1;
    }

    public int getCheck2() {
        return check2;
    }

    public void setCheck2(int check2) {
        this.check2 = check2;
    }

    public int getCheck3() {
        return check3;
    }

    public void setCheck3(int check3) {
        this.check3 = check3;
    }

    public int getCheck4() {
        return check4;
    }

    public void setCheck4(int check4) {
        this.check4 = check4;
    }

    public Knyga(int id, String name, String release_year, String author, String genre,
                 String rarity, int pages, String cover) {
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
                 String cover,int check1,int check2,int check3,int check4) {
        super();
        this.name = name;
        this.release_year = release_year;
        this.pages = pages;
        this.author = author;
        this.genre = genre;
        this.rarity = rarity;
        this.cover = cover;
        this.check1 = check1;
        this.check2 = check2;
        this.check3 = check3;
        this.check4 = check4;
    }
    public Knyga(int id,String name,  String release_year, String author, String genre, String rarity, int pages,
                 String cover,int check1,int check2,int check3,int check4) {
        super();
        this.id = id;
        this.name = name;
        this.release_year = release_year;
        this.pages = pages;
        this.author = author;
        this.genre = genre;
        this.rarity = rarity;
        this.cover = cover;
        this.check1 = check1;
        this.check2 = check2;
        this.check3 = check3;
        this.check4 = check4;
    }
    public Knyga() {
        super();
    }
    @Override
    public String toString() {
        return " Book's Name = " + name; }

}




