package com.api.cucumber.transform;

import java.util.Arrays;
import java.util.List;

import cucumber.api.Transformer;

public class TransformDataList extends Transformer<List<String>>{

	@Override
	public List<String> transform(String laptop) {
		 List<String> data = Arrays.asList(laptop.split(","));
		return data;
	}

}
