package com.webapp.nguyenminh.service;

import java.util.Optional;

import com.webapp.nguyenminh.entity.ImageEntity;
import com.webapp.nguyenminh.entity.InformationCtyDataEntity;

public interface InformationCtyService {
//	List<InformationCtyDataEntity> loadAllInformation();
	Optional<ImageEntity> findImageById(String id);
	InformationCtyDataEntity loadinfor();
	Optional<InformationCtyDataEntity> getInforCty(String id);
	InformationCtyDataEntity saveIinformationCty(InformationCtyDataEntity inforcty);
//	xu ly hinh anh
	ImageEntity saveimage(ImageEntity image);
}
