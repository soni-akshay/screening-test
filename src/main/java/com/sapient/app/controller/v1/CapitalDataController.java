package com.sapient.app.controller.v1;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.app.exception.CapitalDataServiceException;
import com.sapient.app.exception.InvalidFileException;
import com.sapient.app.model.CapitalDataGroupByModel;
import com.sapient.app.service.CapitalDataService;
import com.sapient.app.util.file.FileWriter;
import com.sapient.app.util.file.factory.AbstractFileUtilFactory;

@RestController
@RequestMapping("/api/v1/capitaldata")
public class CapitalDataController {

	private static final Logger logger = LoggerFactory.getLogger(CapitalDataController.class);

	private static final String[] csvHeader = { "City/Country", "Gender", "Average Income(USD)" };

	@Autowired
	private CapitalDataService _capCapitalDataService;

	@Autowired
	private AbstractFileUtilFactory _abstractFileUtilFactory;

	@GetMapping("/report")
	public void exportToCSV(HttpServletResponse response)
			throws IOException, CapitalDataServiceException, InvalidFileException {
		logger.info("Fetching Capital Data.");
		final List<CapitalDataGroupByModel> capitalDataList = this._capCapitalDataService.getGroupedCapitalData();
		logger.info("Capital Data Fetched.");
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=data_" + currentDateTime + ".csv";
		response.setHeader(headerKey, headerValue);

		FileWriter csvWriter = null;
		csvWriter = this._abstractFileUtilFactory.getFileWriter("CSV", response.getWriter());
		logger.info("Writing Capital Data to response.");
		csvWriter.writeHeader(csvHeader);
		for (CapitalDataGroupByModel capitalData : capitalDataList) {
			csvWriter.writeData(getFieldValues(capitalData));
		}
		csvWriter.close();
		logger.info("Capital Data returned as response.");
	}

	private String[] getFieldValues(CapitalDataGroupByModel model) {
		return new String[] { model.getCountry(), model.getGender(), String.valueOf(model.getIncome()) };
	}
}
