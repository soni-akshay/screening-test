package com.sapient.app.service;

import java.util.List;

import com.sapient.app.exception.CapitalDataServiceException;
import com.sapient.app.model.CapitalDataGroupByModel;

public interface CapitalDataService {

	List<CapitalDataGroupByModel> getGroupedCapitalData() throws CapitalDataServiceException;
}
