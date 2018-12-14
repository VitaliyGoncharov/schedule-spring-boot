package com.vitgon.schedule.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.vitgon.schedule.model.Locale;
import com.vitgon.schedule.model.translation.SchoolTranslation;

public class TestStream {
	public static void main(String[] args) {
		Locale locale = new Locale("en");
		Locale locale1 = new Locale("ru");
		SchoolTranslation st1 = new SchoolTranslation(null, locale, "azaza1");
		SchoolTranslation st2 = new SchoolTranslation(null, locale1, "azaza23553");
		
		List<SchoolTranslation> schoolTransl = new ArrayList<>();
		schoolTransl.add(st1);
		schoolTransl.add(st2);
		String title = schoolTransl.stream()
				.filter(x -> x.getLocale() == locale)
				.map(SchoolTranslation::getTitle)
				.collect(Collectors.joining(" - "));
		System.out.println(title);
	}
}
