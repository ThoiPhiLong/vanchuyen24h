package com.webapp.nguyenminh.service;

import java.util.List;

import com.webapp.nguyenminh.entity.SubMenuEntity;

public interface SubMenuService {
	List<SubMenuEntity> getAll();
	SubMenuEntity saveSubmenu(SubMenuEntity submenu);
	void deleteSubmenu(Long id);
}
