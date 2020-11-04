package com.webapp.nguyenminh.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.nguyenminh.entity.SliderHomeEntity;

@Repository
public interface SilderHomeRepository extends JpaRepository<SliderHomeEntity, String> {

}
