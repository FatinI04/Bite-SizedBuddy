package com.example.API;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


import org.json.JSONArray;
import org.json.JSONObject;


public class API {


    private ArrayList<String> mainInfo;


    public API() {
        mainInfo = new ArrayList<String>();
    }


    public static String getData(String endpoint) throws Exception {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");


        StringBuilder content;
        try (BufferedReader buff = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            content = new StringBuilder();
            while ((inputLine = buff.readLine()) != null) {
                content.append(inputLine);
            }
        }
        connection.disconnect();
        return content.toString();
    }


    // Main method to call Open Food Facts API
    public void setAPIData(String product1) {
        try {
            String searchQuery = product1;
            String searchURL = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=" +
                               searchQuery.replace(" ", "+") + "&search_simple=1&json=1";
            String searchResult = getData(searchURL);
            JSONObject searchObj = new JSONObject(searchResult);
            JSONArray products = searchObj.getJSONArray("products");


            if (products.length() == 0) {
                mainInfo.add("No products found.");
                return;
            }


            JSONObject product = products.getJSONObject(0);
            String productName = product.optString("product_name", "Unknown Product");
            String brand = product.optString("brands", "Unknown Brand");
            String ingredients = product.optString("ingredients_text", "No ingredient info");
            String nutritionGrade = product.optString("nutrition_grade_fr", "N/A");


            JSONObject nutrients = product.optJSONObject("nutriments");
            String energy = nutrients != null ? nutrients.optString("energy-kcal_100g", "N/A") : "N/A";
            String fat = nutrients != null ? nutrients.optString("fat_100g", "N/A") : "N/A";
            String sugars = nutrients != null ? nutrients.optString("sugars_100g", "N/A") : "N/A";
            String protein = nutrients != null ? nutrients.optString("proteins_100g", "N/A") : "N/A";


            mainInfo.add( "\n" + "Product: " + productName + "\n" +
                       "Brand: " + brand + "\n" +
                       "Ingredients: " + ingredients + "\n" +
                       "Nutrition Grade: " + nutritionGrade + "\n" +
                       "Per 100g:\n" +
                       "- Energy: " + energy + " kcal\n" +
                       "- Fat: " + fat + " g\n" +
                       "- Sugars: " + sugars + " g\n" +
                       "- Protein: " + protein + " g\n");


        } catch (Exception e) {
            e.printStackTrace();
            mainInfo.add("Error retrieving data.");
        }
    }


    public ArrayList<String> getInfo() {
        return mainInfo;
    }
}
