package com.api.okker.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.okker.dao.Store;
import com.api.okker.repository.StoreRepo;

@Service
public class StoreServiceImpl implements StoreService {
	
	@Autowired
	StoreRepo storeRepo;

	@Override
	public Collection<Store> viewStore() {
		return storeRepo.findAll();
	}

	@Override
	public Collection<Store> searchStore(String keyword) {
		return storeRepo.searchByKey(keyword);
	}

}
