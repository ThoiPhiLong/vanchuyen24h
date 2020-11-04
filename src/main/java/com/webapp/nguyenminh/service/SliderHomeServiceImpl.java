package com.webapp.nguyenminh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.nguyenminh.entity.ImageEntity;
import com.webapp.nguyenminh.entity.SliderHomeEntity;
import com.webapp.nguyenminh.reppository.ImageRepository;
import com.webapp.nguyenminh.reppository.SilderHomeRepository;

@Service
public class SliderHomeServiceImpl implements SliderHomeService {

	@Autowired
	SilderHomeRepository sliderRepository;
	@Autowired 
	ImageRepository imageRepository;
	@Override
	public List<SliderHomeEntity> getAllSiderHome() {
		// TODO Auto-generated method stub
		return sliderRepository.findAll();
	}
	@Override
	public SliderHomeEntity save(SliderHomeEntity sliderhome) {
		// TODO Auto-generated method stub
		return sliderRepository.save(sliderhome);
	}
	@Override
	public ImageEntity saveImg(ImageEntity img) {
		// TODO Auto-generated method stub
		return imageRepository.save(img);
	}
	@Override
	public Optional<SliderHomeEntity> getById(String id) {
		// TODO Auto-generated method stub
		return sliderRepository.findById(id);
	}

}
