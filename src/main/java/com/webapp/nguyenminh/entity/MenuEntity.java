package com.webapp.nguyenminh.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "menu")
public class MenuEntity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long menu_id;
	private String name;
	private String to1;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "menu_fk", referencedColumnName = "menu_id")
	private List<SubMenuEntity> submenu;
	
}
