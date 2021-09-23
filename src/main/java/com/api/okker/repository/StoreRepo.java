package com.api.okker.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.okker.dao.Store;

@Repository
public interface StoreRepo extends JpaRepository<Store, Integer> {
	@Query(value = "SELECT p FROM Store p WHERE storeName LIKE '%' || :key ||  '%'" + 
			"OR Address LIKE '%' || :key ||  '%' " )
	public Collection<Store> searchByKey(@Param("key") String key);
}
