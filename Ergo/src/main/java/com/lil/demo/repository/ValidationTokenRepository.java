package com.lil.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lil.demo.model.ValidationToken;

public interface ValidationTokenRepository extends JpaRepository<ValidationToken, Integer> {

	public ValidationToken findByToken(String stringToken);
	
}
