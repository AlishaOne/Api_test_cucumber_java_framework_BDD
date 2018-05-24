package com.api.cucumber.transform;

import cucumber.api.Transformer;

public class TransformDataUser extends Transformer<User> {

	@Override
	public User transform(String value) {
		
		return new User();
	}


}