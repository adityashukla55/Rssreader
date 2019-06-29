package com.project.dbwt.model;

import com.sun.syndication.feed.synd.SyndEntryImpl;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "News")
public class NewsAggregatorModel {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String link;
    @Column
    private String author;
    @Column
    private Date publishedDate;
    @Column
    private Date updatedDate;

    public NewsAggregatorModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }



    public NewsAggregatorModel(Long id, String title, String link, String author, Date publishedDate, Date updatedDate) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.author = author;
        this.publishedDate = publishedDate;
        this.updatedDate = updatedDate;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "NewsAggregatorModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", author='" + author + '\'' +
                ", publishedDate=" + publishedDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}