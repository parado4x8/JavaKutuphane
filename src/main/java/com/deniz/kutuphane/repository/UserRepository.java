package com.deniz.kutuphane.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.deniz.kutuphane.entities.Users;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<Users, Integer> {
	
	
	public Optional<Users> findByUsername(String username);

}
