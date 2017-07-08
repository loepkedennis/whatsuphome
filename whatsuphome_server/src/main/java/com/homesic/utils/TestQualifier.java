package com.homesic.utils;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

public class TestQualifier {
	
	@Qualifier
	@Target({FIELD, TYPE})
	@Retention(RUNTIME)
	public @interface MyService{
	}

}
