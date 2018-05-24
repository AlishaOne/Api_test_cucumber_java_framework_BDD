package com.api.cucumber.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Laptop")
public class LaptopBag {
	
	public String BrandName;
	public String Id;
	public String LaptopName;
	public  Features Features;
	
	/*	add 
	 * @XmlElement(name="BrandName")
	 * before getter method
	 *if any label is not same as content :
	 *
	 *The first letter should be upcase as :BrandName 
  <brandName>Lenveno</brandName>
  <features>
    <feature>8GB RAM</feature>
    <feature>2TB Hard Drive</feature>
  </features>
  <id>729</id>
  <laptopName>ThinkPad X61</laptopName>
	 *
	@XmlElement(name="BrandName")*/
	public String getBrandName() {
		return BrandName;
	}
	public void setBrandName(String brandName) {
		this.BrandName = brandName;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		this.Id = id;
	}
	public String getLaptopName() {
		return LaptopName;
	}
	public void setLaptopName(String laptopName) {
		this.LaptopName = laptopName;
	}
	public Features getFeatures() {
		return Features;
	}
	public void setFeatures(Features features) {
		this.Features = features;
	}

}


