package com.webapp.nguyenminh.reppository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.nguyenminh.entity.SubMenuEntity;

@Repository
public interface SubMenuRepository extends JpaRepository<SubMenuEntity, Long> {

}
