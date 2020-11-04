package com.webapp.nguyenminh.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "informationcty")
public class InformationCtyDataEntity {
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	private String title;
	private String name;
	private String email;
	private String phone;
	private String diachi;
	private String description;
	
	@Column(name = "create_datetime")
	@DateTimeFormat(pattern="yyyy.MM.dd")
	//@JsonFormat(pattern="yyyy-MM-dd")
	private LocalDateTime createdatatime;
	
	@EqualsAndHashCode.Exclude
	@OneToOne
	@JoinColumn(name="img_id")
	private ImageEntity img;
}
