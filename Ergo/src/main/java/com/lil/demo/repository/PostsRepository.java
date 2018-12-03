package com.lil.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lil.demo.model.Post;


public interface PostsRepository extends JpaRepository<Post, Integer> {
	
}
