package com.daycare.app.backend.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "review")
public class Review {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
	
	@Column(name = "caregiver_id")
    private Long caregiverId;
	
	@NotNull
	private String title;
	
	@NotNull
	private String body;
	
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp time = new Timestamp(System.currentTimeMillis());;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getCaregiverId() {
		return caregiverId;
	}

	public void setCaregiverId(Long caregiverId) {
		this.caregiverId = caregiverId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public Review(Long id, User user, Long caregiverId,  String title,  String body,
			Timestamp time) {
		super();
		this.id = id;
		this.user = user;
		this.caregiverId = caregiverId;
		this.title = title;
		this.body = body;
		this.time = time;
	}

	public Review(){}
}
