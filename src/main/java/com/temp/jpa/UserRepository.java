package com.temp.jpa;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.temp.jpa.entity.UserEntity;
import com.temp.jpa.entity.UserQueryCondition;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class UserRepository {
	
	@PersistenceContext
    private EntityManager entityManager;
	
	@Autowired
	private UserJpaRepository userJpaRepository;
	
	public UserJpaRepository getJpaRepository() {
		return userJpaRepository;
	}

	@SuppressWarnings("unchecked")
	public List<UserEntity> findByQueryCondition(UserQueryCondition condition) {
		StringBuilder sql = new StringBuilder("SELECT * FROM UserEntity WHERE 1=1");
        List<Object> parameters = new ArrayList<>();

        if (condition != null) {
            sql.append(" AND userName = ?");
            parameters.add(condition);
        }

        Query query = entityManager.createNativeQuery(sql.toString(), UserEntity.class);

        for (int i = 0; i < parameters.size(); i++) {
            query.setParameter(i + 1, parameters.get(i));
        }

        return (List<UserEntity>) query.getResultList();
	}
	
}
