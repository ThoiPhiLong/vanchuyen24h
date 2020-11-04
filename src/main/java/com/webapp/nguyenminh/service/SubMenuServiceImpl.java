package com.webapp.nguyenminh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.nguyenminh.entity.SubMenuEntity;
import com.webapp.nguyenminh.reppository.SubMenuRepository;

@Service
public class SubMenuServiceImpl implements SubMenuService {

	@Autowired SubMenuRepository subMenuRepository;
	
	@Override
	public List<SubMenuEntity> getAll() {
		// TODO Auto-generated method stub
		return subMenuRepository.findAll();
	}

	@Override
	public SubMenuEntity saveSubmenu(SubMenuEntity submenu) {
		// TODO Auto-generated method stub
		return subMenuRepository.save(submenu);
	}

	@Override
	public void deleteSubmenu(Long id) {
		// TODO Auto-generated method stub
		subMenuRepository.deleteById(id);
	}

}
