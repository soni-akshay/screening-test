package com.sapient.app.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sapient.app.exception.DataLoaderException;
import com.sapient.app.exception.InvalidFileException;
import com.sapient.app.model.CapitalDataModel;
import com.sapient.app.service.CapitalDataLoaderService;
import com.sapient.app.util.file.FileReader;
import com.sapient.app.util.file.factory.AbstractFileUtilFactory;

@Service("capitalDataLoaderService")
public class CapitalDataLoaderServiceImpl implements CapitalDataLoaderService {

	private static final Logger logger = LoggerFactory.getLogger(CapitalDataLoaderServiceImpl.class);

	@Autowired
	private AbstractFileUtilFactory _abstractFileUtilFactory;

	@Override
	public List<CapitalDataModel> loadCapitalSheetDataFromFile(String filePath) throws DataLoaderException {
		try {
			FileReader reader = _abstractFileUtilFactory.getFileReader(filePath);
			List<String[]> rows = reader.readAllRows();
			if (CollectionUtils.isEmpty(rows)) {
				logger.info("No data present in Capital Data Sheet.");
				return Collections.emptyList();
			}
			logger.info("Data found in Capital Data Sheet. Size : [{}]", rows.size());
			final List<CapitalDataModel> capitalData = new ArrayList<>();
			int i = 0;
			for (String[] rowData : rows) {
				if (i == 0) {
					// Skip as its a header row
					i++;
					continue;
				}
				final Double value = Double.valueOf(rowData[4]);
				final CapitalDataModel model = new CapitalDataModel(rowData[0], rowData[1], rowData[2], rowData[3],
						value);
				capitalData.add(model);
			}
			return capitalData;
		} catch (FileNotFoundException | InvalidFileException e) {
			throw new DataLoaderException("Invalid File", e);
		} catch (IOException e) {
			logger.error("IO Exception occurred while loading data, Cause : [{}]", e.getCause());
			throw new DataLoaderException("File IO Exception", e);
		} catch (IllegalArgumentException e) {
			logger.error("IllegalArgumentException occurred while loading data, Cause : [{}]", e.getCause());
			throw new DataLoaderException("Invalid Data File", e);
		}
	}

}
