package com.webapp.nguyenminh.service;

import java.util.List;
import java.util.Optional;

import com.webapp.nguyenminh.entity.MenuEntity;

public interface MenuService {
	List<MenuEntity> getAll();
	Optional<MenuEntity> getIdMenu(Long id);
	MenuEntity saveMenu(MenuEntity menu);
	void deleteMenu(Long id);
}
