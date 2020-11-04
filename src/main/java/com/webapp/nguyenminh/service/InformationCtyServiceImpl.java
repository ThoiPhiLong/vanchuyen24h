package com.webapp.nguyenminh.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.nguyenminh.entity.ImageEntity;
import com.webapp.nguyenminh.entity.InformationCtyDataEntity;
import com.webapp.nguyenminh.reppository.ImageRepository;
import com.webapp.nguyenminh.reppository.InformationCtyRepository;

@Service
public class InformationCtyServiceImpl implements InformationCtyService {

	@Autowired
	InformationCtyRepository inforctyRepositi;
	@Autowired
	ImageRepository imgRepository;
//	@Override
//	public List<InformationCtyDataEntity> loadAllInformation() {
//		// TODO Auto-generated method stub
//		return inforctyRepositi.findAll();
//	}
	@Override
	public Optional<ImageEntity> findImageById(String id) {
		// TODO Auto-generated method stub
		return imgRepository.findById(id);
	}

	@Override
	public InformationCtyDataEntity loadinfor() {
		// TODO Auto-generated method stub
		InformationCtyDataEntity info = inforctyRepositi.findAll().get(0);
		return info;
	}

	@Override
	public InformationCtyDataEntity saveIinformationCty(InformationCtyDataEntity inforcty) {
		// TODO Auto-generated method stub
		return inforctyRepositi.save(inforcty);
	}

	@Override
	public ImageEntity saveimage(ImageEntity image) {
		// TODO Auto-generated method stub
		return imgRepository.save(image);
	}

	@Override
	public Optional<InformationCtyDataEntity> getInforCty(String id) {
		// TODO Auto-generated method stub
		return inforctyRepositi.findById(id);
	}


	


}
