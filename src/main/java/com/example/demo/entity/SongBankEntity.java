package com.example.demo.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="songbank")
public class SongBankEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String artist;
	
	private String cover_artist;
		
	private String title;
	
	private String url;
	
	private String genre;
	
	private Date post_time;
	
	private String event_id;
	
}

