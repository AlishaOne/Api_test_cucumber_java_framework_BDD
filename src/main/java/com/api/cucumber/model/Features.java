package com.api.cucumber.model;

import java.util.ArrayList;
import java.util.List;

public class Features {

	public List<String> Feature;
	//Constructor
	public Features() {
		Feature=new ArrayList<String>();
	}
	//getter
	public List<String> getFeature() {
		return Feature;
	}
	//setter
	public void setFeature(List<String> feature) {
		Feature = feature;
	}
	

}
