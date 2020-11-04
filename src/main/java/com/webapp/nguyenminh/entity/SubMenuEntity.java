package com.webapp.nguyenminh.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "submenu")
public class SubMenuEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long submenu_id;
	private String name;
	private String to1;
}
