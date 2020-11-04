package com.webapp.nguyenminh.service;

import java.util.List;
import java.util.Optional;

import com.webapp.nguyenminh.entity.ImageEntity;
import com.webapp.nguyenminh.entity.SliderHomeEntity;

public interface SliderHomeService {
	List<SliderHomeEntity> getAllSiderHome();
	SliderHomeEntity save(SliderHomeEntity sliderhome);
	Optional<SliderHomeEntity> getById(String id);
	ImageEntity saveImg(ImageEntity img);
}
