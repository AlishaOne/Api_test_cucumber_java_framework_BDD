package com.api.cucumber.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.annotations.JsonAdapter;

public class Helper {
	public static String findId = 694 + "";
	private static final String BRANDNAME = "BrandName";
	private static final String FEATURES = "Features";
	private static final String FEATURE = "Feature";
	private static final String _ID = "Id";
	private static final String LAPTOPNAME = "LaptopName";

	public static List<JSONObject> getJsonArrayItem(String str) {
		JSONArray jsonArray = new JSONArray(str);
		System.out.println("jsonArray is " + jsonArray);
		List<JSONObject> jsonItems = null;
		if (jsonArray.length() == 1) {
			jsonItems = (List<JSONObject>) IntStream.range(0, jsonArray.length())
					.mapToObj(index -> (JSONObject) jsonArray.get(index));
			System.out.println("jn is " + jsonItems);

		} else {
			jsonItems = IntStream.range(0, jsonArray.length()).mapToObj(index -> (JSONObject) jsonArray.get(index))
					.collect(Collectors.toList());
			// System.out.println("jn is "+jsonItems);
			// System.out.println("item is
			// "+jsonItems.get(0).get("BrandName").toString().contains("De"));
		}
		return jsonItems;
	}

	public static String genaratedUniqueId(int n) {
		String id = (int) (Math.random() * n) + "";
		System.out.println(id);
		return id;

	}

	public static String writeDynamicDataToFile(String filePath, String oldId, String newId) {
		File file = new File(filePath);
		BufferedReader reader = null;
		FileWriter writer = null;
		String oldContent = "";
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				// System.out.println("old content: " + oldContent);
				line = reader.readLine();
			}

			String newContent = oldContent.replaceAll(oldId, newId);
			// System.out.println("new content: " + newContent);
			writer = new FileWriter(file);
			writer.write(newContent);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return newId;
	}

	public static JSONObject readDynamicDataFromFile(String filePath) {
		File file = new File(filePath);
		BufferedReader reader = null;
		// FileWriter writer = null;
		String content = "";
		JSONObject json = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			while (line != null) {
				content = content + line + System.lineSeparator();
				// System.out.println("content: " + content);
				line = reader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		// for debug
		// System.out.println("final content: ");
		// System.out.println(content);
		// Pattern p = Pattern.compile("<([A-Za-z0-9]*)>");
		Pattern p = Pattern.compile("<(.*)>");
		Matcher m = p.matcher(content);
		if (m.find()) {
			JSONObject jsonXml = XML.toJSONObject(content.toString());
			// int indentFactor = 4;
			json = new JSONObject(jsonXml.get("Laptop").toString());
			// for debug
			// System.out.println("xml laptop:" + jsonXml.get("Laptop").toString());
			// System.out.println("xml j:" + json);

		} else {
			json = new JSONObject(content);
		}

		return json;

	}
	
	public static String readDynamicDataFromFileWithKey(String filePath, String key) {
		File file = new File(filePath);
		BufferedReader reader = null;
		// FileWriter writer = null;
		String content = "";
		JSONObject json = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();

			while (line != null) {
				content = content + line + System.lineSeparator();
				// System.out.println("content: " + content);
				line = reader.readLine();

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		// for debug
		// System.out.println("final content: ");
		// System.out.println(content);
		// Pattern p = Pattern.compile("<([A-Za-z0-9]*)>");
		Pattern p = Pattern.compile("<(.*)>");
		Matcher m = p.matcher(content);
		if (m.find()) {
			JSONObject jsonXml = XML.toJSONObject(content.toString());
//			int indentFactor = 4;
			json = new JSONObject(jsonXml.get("Laptop").toString());
			//for debug
			// System.out.println("xml laptop:" + jsonXml.get("Laptop").toString());
			// System.out.println("xml j:" + json);

		} else {
			json = new JSONObject(content);
		}
		return json.get(key).toString();

	}

	public static String xmlBody(String id) {
		String body = "<Laptop>" + "    <BrandName>Dell xml " + id + "</BrandName>" + "    <Features>"
				+ "        <Feature>8GB RAM</Feature>" + "        <Feature>1TB Hard Drive</Feature>" + "    </Features>"
				+ "    <Id>" + id + "</Id>" + "    <LaptopName>Latitude xml " + id + "</LaptopName>" + "</Laptop>";
		return body;
	}

	public static String xmlBodyUpdate(String id) {
		String body = "<Laptop>" + "    <BrandName>Dell xml update " + id + "</BrandName>" + "    <Features>"
				+ "        <Feature>8GB RAM</Feature>" + "        <Feature>1TB Hard Drive</Feature>" + "    </Features>"
				+ "    <Id>" + id + "</Id>" + "    <LaptopName>Latitude xml " + id + "</LaptopName>" + "</Laptop>";
		return body;
	}

	public static String jsonBody(String id) {
		String body = "{" + " \"BrandName\": \"Dell string " + id + "\"," + " \"Features\": {"
				+ "  \"Feature\": [\"8GB RAM\"," + "  \"1TB Hard Drive\"]" + " }," + " \"Id\":" + id + ","
				+ " \"LaptopName\": \"Latitude " + id + "\"" + "}";
		return body;
	}

	// create jsonData at runtime
	public static String getJsonBody(String brandName, String myId, List<String> feature, String laptopName) {
		JsonObject jsonObj = new JsonObject();

		jsonObj.addProperty(BRANDNAME, brandName);
		jsonObj.addProperty(LAPTOPNAME, laptopName);
		jsonObj.addProperty(_ID, myId == null ? genaratedUniqueId(1000) : myId);

		JsonObject featuresObj = new JsonObject();
		JsonArray array = getJsonArray(feature);
		featuresObj.add(FEATURE, array);
		jsonObj.add(FEATURES, featuresObj);
		return jsonObj.toString();

	}

	private static JsonArray getJsonArray(List<String> feature) {
		JsonArray array = new JsonArray();
		for (String str : feature) {
			array.add(str);

		}
		return array;
	}

}
