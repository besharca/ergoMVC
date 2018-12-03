package com.lil.demo.repository;
 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.lil.demo.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, Integer>{
	
	public UserModel findByUsername(String username);
	public UserModel findByEmail(String email);
	
	@Modifying	
    // @Query("delete from VerificationToken t where t.expiryDate <= ?1")
	@Query("update UserModel t set t.enabled=true where t.email=?1")
    public void enableAccount(String email);
	 
}
