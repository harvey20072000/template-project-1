package com.temp.jpa;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.temp.jpa.entity.UserEntity;

@Repository
public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {

//	@Query("FROM UserEntity as u WHERE u.userName = :userNmae")
//	public UserEntity findByUserName(Param(value = "userName") String userName);
	
	@Query("FROM UserEntity as u")
    public List<UserEntity> findByRoleKeyPaging(Pageable pageable);
	
//	@Query("FROM UserEntity as u WHERE u.userName = :userName AND u.password = :password")
//    public List<UserEntity> findByUserNameAndPassword(@Param(value = "userName") String userName, 
//    													@Param(value = "password") String password);
	
    public List<UserEntity> findByUserNameAndPassword(String userName, String password);
    
    public List<UserEntity> findByUserName(String userName);
	
}
