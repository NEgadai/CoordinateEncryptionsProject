package Encryption;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;


public class googleMapsAPI {
	public static Double[] getCordinates(String address)throws ParseException, IOException{
		Double[] cordins= new  Double[2];
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		String addressInput = address;

	    String key = "AIzaSyDzq7jShLJY1pb_KMjrkmJYA1dlDmZnt3c";
		Elements Source = Jsoup.connect(url+addressInput+"&key="+key).ignoreContentType(true).get().select("body");
		JSONParser parser = new JSONParser(); 
		JSONObject bodySource = (JSONObject) parser.parse(Source.text());
		JSONArray resultsElement = (JSONArray) bodySource.get("results");
		JSONObject toObj = null;
		try{
			toObj = (JSONObject) resultsElement.get(0);
		}catch (IndexOutOfBoundsException e){
			System.out.println("Incorrect address");
			return null;
		}
		JSONObject geometryElement = (JSONObject) toObj.get("geometry");
		JSONObject locationElement = (JSONObject) geometryElement.get("location");
		cordins[0] = (Double) locationElement.get("lat");
		cordins[1] = (Double) locationElement.get("lng");

		return cordins;
	}
	public static String getChar(Double[] Cord)throws ParseException, IOException{
		String url = "https://maps.googleapis.com/maps/api/geocode/json?address=";
		String cordInput = Cord[0]+","+Cord[1];
	    String key = "AIzaSyDzq7jShLJY1pb_KMjrkmJYA1dlDmZnt3c";
		Elements Source = Jsoup.connect(url+cordInput+"&key="+key).ignoreContentType(true).get().select("body");
		JSONParser parser = new JSONParser(); 
		JSONObject bodySource = (JSONObject) parser.parse(Source.text());
		JSONArray resultsElement = (JSONArray) bodySource.get("results");
		JSONObject toObj = null;
		try{
			toObj = (JSONObject) resultsElement.get(0);
		}catch (IndexOutOfBoundsException e){
			System.out.println("Incorrect Coordinates");
			return null;
		}
		JSONArray address_componentsElement = (JSONArray) toObj.get("address_components");
		JSONObject thirdElement = null;

		String name = null;
		try{
			thirdElement = (JSONObject) address_componentsElement.get(2);
			name = (String)thirdElement.get("long_name");
		}catch(IndexOutOfBoundsException e){
			return null;
		}
		if(name != null)
			return String.valueOf(name.charAt(0)).toLowerCase();
		return name;
	}

}
