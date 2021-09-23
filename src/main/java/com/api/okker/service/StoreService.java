package com.api.okker.service;

import java.util.Collection;

import com.api.okker.dao.Store;

public interface StoreService {
	public Collection<Store> viewStore();
	public Collection<Store> searchStore(String keyword);
}
