package com.xebia.automating_flyers;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xebia.automating_flyers.model.MailFlayersModel;
import com.xebia.automating_flyers.service.MailService;
import com.xebia.automating_flyers.utility.SendEmail;

@Component
public class MailSchedular {

	private static final String CLASS_NAME = MailSchedular.class.getName();

	private static final Logger LOGGER = LoggerFactory.getLogger(CLASS_NAME);

	@Autowired
	MailService mailService;

	@Scheduled(cron = "*/30 * * * * *")
	// @Scheduled(cron = "0 0 13 * * ?")
	// @Scheduled(cron = "0 32 13 * * ?")
	// @Scheduled(cron = "0 0 10 * * ?")
	public void mailScheduling() {

		// String day = GetDayFromDate.checkDay();
		String day = "Tuesday";
		LOGGER.info("Day:" + day);
		if (day.equalsIgnoreCase("Tuesday")) {

			LOGGER.info("Scheduling:" + new Date());

			int records = totalNumberOfRecords();
			LOGGER.info("Total Number Of records:" + records);

			getAllRecords(records);
			// getRecordById(count);
		}
	}

	private void getAllRecords(int records) {
		Iterable<MailFlayersModel> allrecords = mailService.getAllRecords();

		for (MailFlayersModel model : allrecords) {
			String status = model.getStatus();
			LOGGER.info("Id:" + model.getId());
			LOGGER.info("*******Status********" + status);

			if (status.equalsIgnoreCase("NotSent")) {

				int id = model.getId();
				String subject = model.getSubject();
				String imagePath = model.getImagePath();

				// EmailSend2.setEmail(subject, imagePath);
				String mailStatus = SendEmail.sendEmail(subject, imagePath);
				LOGGER.info("mailStatus:" + mailStatus);
				if (mailStatus.equalsIgnoreCase("success")) {
					String updateStatus = "Sent";
					mailService.updateRecord(id, updateStatus);
					if (model.getId() == records) {

						mailService.updateAllRecords();
						LOGGER.info("All records has been updated:");
					}
					break;

				} else {
					String updateStatus = "NotSent";
					mailService.updateRecord(id, updateStatus);
				}

			}
		}

	}

	private int totalNumberOfRecords() {

		int numberOfRecords = mailService.getTotalNumberOfRecords();

		return numberOfRecords;
	}

	private Optional<MailFlayersModel> getRecordById(int id) {

		// LOGGER.info("inside getRecordById method of ");
		Optional<MailFlayersModel> result = mailService.getRecordById(id);
		LOGGER.info("********RESULT**********" + result);
		String status = result.get().getStatus();
		LOGGER.info("Status is:" + status);

		if (status.equalsIgnoreCase("NotSent")) {

			String subject = result.get().getSubject();
			String imagePath = result.get().getImagePath();

			// EmailSend2.setEmail(subject, imagePath);
			String mailStatus = SendEmail.sendEmail(subject, imagePath);
			LOGGER.info("mailStatus:" + mailStatus);
			if (mailStatus.equalsIgnoreCase("success")) {
				String updateStatus = "Sent";
				mailService.updateRecord(id, updateStatus);

			} else {
				String updateStatus = "NotSent";
				mailService.updateRecord(id, updateStatus);
			}

		}

		else {
			LOGGER.info("Mail is already Sent");
		}

		return result;

	}

}
