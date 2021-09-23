package com.api.okker.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.okker.dao.Store;
import com.api.okker.service.StoreService;

@RestController
@RequestMapping("/kyn")
@CrossOrigin("http://localhost:3000")
public class StoreController {
	@Autowired
	StoreService storeservice;
	
	@GetMapping("/store")
	@PreAuthorize("hasRole('USER')")
	public Collection<Store> viewAllStores(){
		System.out.println("in the controller");
		return storeservice.viewStore();
	}
	
	@GetMapping("/store/search/{keyword}")
	public Collection<Store> searchStoreByKeyword(@PathVariable String keyword){
		return storeservice.searchStore(keyword);
	}
}
