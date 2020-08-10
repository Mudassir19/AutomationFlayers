package com.xebia.automating_flyers.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.xebia.automating_flyers.dao.MailRepository;
import com.xebia.automating_flyers.model.MailFlayersModel;

@Service
public class MailService {
	
	private static final String CLASS_NAME = MailService.class.getName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	MailRepository repository;

	public Optional<MailFlayersModel> getRecordById(int id) {

		LOGGER.info("inside getRecordById Method of: "+CLASS_NAME);
		try {
			return repository.findById(id);

		} catch (Exception e) {
			LOGGER.info("Exception occured in getRecordById() " + e);
			return null;

		}

	}
	
	public Iterable<MailFlayersModel> getAllRecords() {

		LOGGER.info("inside getAllRecords Method of "+CLASS_NAME);
		try {
			return repository.findAll();

		} catch (Exception e) {
			LOGGER.info("Exception occured in getAllRecords" + e);
			return null;

		}

	}
	

	public MailFlayersModel updateRecord(int id, String newStatus) {

		LOGGER.info("inside updateRecord Method of:"+CLASS_NAME);
		try {
			MailFlayersModel entities = repository.findById(id).get();
			entities.setStatus(newStatus);
			MailFlayersModel updatedRecords = repository.save(entities);
			LOGGER.info("status has been updated "+newStatus);
			return updatedRecords;
		} catch (Exception e) {
			LOGGER.info("Exception occured in updateRecord() " + e);
			return null;
		}
	}

	public int getTotalNumberOfRecords() {

		int total = (int) repository.count();
		return total;
	}

	public Iterable updateAllRecords() {

		LOGGER.info("inside updateAllRecord Method of:"+CLASS_NAME);
		try {
			Iterable<MailFlayersModel> entities = repository.findAll();

			for (MailFlayersModel model : entities) {

				model.setStatus("NotSent");
			}

			Iterable<MailFlayersModel> updatedRecords = repository.saveAll(entities);

			LOGGER.info("status has been updated");
			return updatedRecords;
		} catch (Exception e) {
			LOGGER.info("Exception occured in updateRecord() " + e);
			return null;
		}
	}

}
