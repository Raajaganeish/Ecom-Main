package com.Angular.Entity;


import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String author;
    private int vol;
    private String category;

    private String dop;
    private int pages;

    private String isAvailable;
    private int price;
    private String imgPath;

    public Book() {
    }

    public Book(String title, String author, int vol, String category, String dop, int pages, String isAvailable, int price, String imgPath) {
        this.title = title;
        this.author = author;
        this.vol = vol;
        this.category = category;
        this.dop = dop;
        this.pages = pages;
        this.isAvailable = isAvailable;
        this.price = price;
        this.imgPath = imgPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVol() {
        return vol;
    }

    public void setVol(int vol) {
        this.vol = vol;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDop() {
        return dop;
    }

    public void setDop(String dop) {
        this.dop = dop;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String isAvailable() {
        return isAvailable;
    }

    public void setAvailable(String available) {
        isAvailable = available;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", vol=" + vol +
                ", category='" + category + '\'' +
                ", dop='" + dop + '\'' +
                ", pages=" + pages +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
