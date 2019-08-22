package com.laptrinhjavaweb.utils;

import java.io.BufferedReader;
import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpUtil {

	private String value;

	public HttpUtil(String value) {
		this.value = value;
	}

	public <T> T toModel(Class<T> tClass) {
		try {
			return new ObjectMapper().readValue(value, tClass);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public Long[] toLongArray() {
		JSONObject jsonObject = new JSONObject(value);
		JSONArray jsonArray = jsonObject.getJSONArray("ids");
		Long[] ids = new Long[jsonArray.length()];
		for(int i = 0; i<jsonArray.length();i++ ) {
			ids[i] = jsonArray.getLong(i);
		}
		return ids;
	}
	
	public static HttpUtil of(BufferedReader reader) {
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new HttpUtil(sb.toString());
	}
}
