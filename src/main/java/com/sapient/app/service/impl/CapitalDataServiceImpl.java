package com.sapient.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sapient.app.exception.CapitalDataServiceException;
import com.sapient.app.exception.DataLoaderException;
import com.sapient.app.exception.InvalidCurrencyException;
import com.sapient.app.model.CapitalDataGroupByModel;
import com.sapient.app.model.CapitalDataModel;
import com.sapient.app.service.CapitalDataLoaderService;
import com.sapient.app.service.CapitalDataService;
import com.sapient.app.util.currency.model.Currency;
import com.sapient.app.util.currency.service.CurrencyService;

@Service("capitalDataService")
public class CapitalDataServiceImpl implements CapitalDataService {

	private static final Logger logger = LoggerFactory.getLogger(CapitalDataServiceImpl.class);

	@Autowired
	private CapitalDataLoaderService _capCapitalDataLoaderService;

	@Autowired
	private CurrencyService _currencyService;

	private final String tempFilePath = "sample_input.csv";

	@Override
	public List<CapitalDataGroupByModel> getGroupedCapitalData() throws CapitalDataServiceException {
		try {
			logger.info("Loading CapitalData from file :: [{}]", tempFilePath);
			final List<CapitalDataModel> capitalDataModels = _capCapitalDataLoaderService
					.loadCapitalSheetDataFromFile(tempFilePath);
			capitalDataModels.stream().forEach(ele -> {
				ele.setIncome(convertToUsd(ele.getIncome(), ele.getCurrency()));
			});
			return this.getAveragedSortedData(capitalDataModels);
		} catch (DataLoaderException | InvalidCurrencyException e) {
			throw new CapitalDataServiceException(String.format("Data Loading Failed. Cause :: %s", e.getMessage()), e);
		}
	}

	private List<CapitalDataGroupByModel> getAveragedSortedData(List<CapitalDataModel> capitalDataModels) {
		final List<Function<CapitalDataModel, ?>> groupByFunctions = getGroupingFunctionModel();
		final Map<List, Double> groupByCapitalDataModel = getGroupedCapitalData(capitalDataModels,
				groupByFunctions.toArray(new Function[groupByFunctions.size()]));
		return groupByCapitalDataModel.entrySet().stream()
				.map(k -> new CapitalDataGroupByModel(String.valueOf(k.getKey().get(0)),
						String.valueOf(k.getKey().get(1)), k.getValue().doubleValue()))
				.sorted().collect(Collectors.toList());
	}

	private List<Function<CapitalDataModel, ?>> getGroupingFunctionModel() {
		// Filter will be passed as an argument and based on filter we can add the
		// grouping functions dynamically. For now going with 2
		final List<Function<CapitalDataModel, ?>> list = new ArrayList<>();
		final Function<CapitalDataModel, ?> function1 = m -> StringUtils.isEmpty(m.getCountry()) ? m.getCity()
				: m.getCountry();
		final Function<CapitalDataModel, ?> function2 = m -> m.getGender();
		list.add(function1);
		list.add(function2);
		return list;
	}

	@SafeVarargs
	private Map<?, Double> getGroupedCapitalData(List<CapitalDataModel> data,
			Function<CapitalDataModel, ?>... groupingFunc) {
		return data.stream()
				.collect(Collectors.groupingBy(
						cl -> Stream.of(groupingFunc).map(f -> f.apply(cl)).collect(Collectors.toList()),
						Collectors.averagingDouble(CapitalDataModel::getIncome)));
	}

	private Double convertToUsd(Double value, String currency) {
		// Convert the currency to USD
		final Currency currencyModel = _currencyService.getCurrency(currency);
		if (currencyModel == null) {
			throw new InvalidCurrencyException(String.format("Currency not supported.", currency));
		}
		return value * currencyModel.getBaseRate().doubleValue();
	}

}
