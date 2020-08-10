package com.xebia.automating_flyers.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.xebia.automating_flyers.model.MailFlayersModel;

@Repository
public interface MailRepository extends CrudRepository<MailFlayersModel,Integer> {

	
	 
}
