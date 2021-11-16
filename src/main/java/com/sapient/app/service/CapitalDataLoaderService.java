package com.sapient.app.service;

import java.util.List;

import com.sapient.app.exception.DataLoaderException;
import com.sapient.app.model.CapitalDataModel;

public interface CapitalDataLoaderService {
	List<CapitalDataModel> loadCapitalSheetDataFromFile(String filePath) throws DataLoaderException;
}
