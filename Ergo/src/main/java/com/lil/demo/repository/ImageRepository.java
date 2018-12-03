package com.lil.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lil.demo.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Integer> {
	public List<ImageModel> findAllByUserName(String userName);
}
