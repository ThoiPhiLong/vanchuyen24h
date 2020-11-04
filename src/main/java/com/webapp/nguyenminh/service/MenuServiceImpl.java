package com.webapp.nguyenminh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.nguyenminh.entity.MenuEntity;
import com.webapp.nguyenminh.reppository.MenuRepository;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired 
	MenuRepository menuRepository;
	
	@Override
	public List<MenuEntity> getAll() {
		// TODO Auto-generated method stub
		return menuRepository.findAll();
	}

	@Override
	public MenuEntity saveMenu(MenuEntity menu) {
		// TODO Auto-generated method stub
		return menuRepository.save(menu);
	}

	@Override
	public Optional<MenuEntity> getIdMenu(Long id) {
		// TODO Auto-generated method stub
		return menuRepository.findById(id);
	}

	@Override
	public void deleteMenu(Long id) {
		// TODO Auto-generated method stub
		menuRepository.deleteById(id);
	}


}
