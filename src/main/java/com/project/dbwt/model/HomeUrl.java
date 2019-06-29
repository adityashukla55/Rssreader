package com.project.dbwt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "homeurl")
public class HomeUrl {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String link;
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
	public HomeUrl() {}
	
	public HomeUrl(Long id, String title, String link) {
		super();
		this.id = id;
		this.title = title;
		this.link = link;
	}

	public HomeUrl( String title, String link) {
	
	
		this.title = title;
		this.link = link;
	}

}