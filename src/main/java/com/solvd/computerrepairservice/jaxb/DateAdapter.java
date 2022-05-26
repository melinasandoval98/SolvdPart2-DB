package com.solvd.computerrepairservice.jaxb;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DateAdapter extends XmlAdapter<String, Date> {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String marshal(Date date) throws Exception {
		return dateFormat.format(date);
	}

	@Override
	public Date unmarshal(String date) throws Exception {
		return dateFormat.parse(date);
	}

}