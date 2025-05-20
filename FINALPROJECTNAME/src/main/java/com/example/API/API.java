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
    private int numProducts = 0;
    private JSONArray cachedProducts = null;
    private String lastQuery = "";


    public API() {
        mainInfo = new ArrayList<>();
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


    public void setAPIData(String product1, int display) {
        try {
            // If new search term, reset cache
            if (!product1.equalsIgnoreCase(lastQuery)) {
                String searchQuery = product1;
                String searchURL = "https://world.openfoodfacts.org/cgi/search.pl?search_terms=" +
                                   searchQuery.replace(" ", "+") + "&search_simple=1&json=1";
                String searchResult = getData(searchURL);
                JSONObject searchObj = new JSONObject(searchResult);
                cachedProducts = searchObj.getJSONArray("products");


                lastQuery = product1;
                numProducts = 0;
                mainInfo.clear(); // clear old results
            }


            if (cachedProducts == null || cachedProducts.length() == 0) {
                mainInfo.add("No products found.");
                return;
            }


            int end = Math.min(numProducts + display, cachedProducts.length());
            for (int i = numProducts; i < end; i++) {
                JSONObject product = cachedProducts.getJSONObject(i);
                String productName = product.optString("product_name", "Unknown Product");
                String brand = product.optString("brands", "Unknown Brand");
                String ingredients = product.optString("ingredients_text", "No ingredient info");
                String nutritionGrade = product.optString("nutrition_grade_fr", "N/A");


                JSONObject nutrients = product.optJSONObject("nutriments");
                String energy = nutrients != null ? nutrients.optString("energy-kcal_100g", "N/A") : "N/A";
                String fat = nutrients != null ? nutrients.optString("fat_100g", "N/A") : "N/A";
                String sugars = nutrients != null ? nutrients.optString("sugars_100g", "N/A") : "N/A";
                String protein = nutrients != null ? nutrients.optString("proteins_100g", "N/A") : "N/A";


                mainInfo.add(
                    "\nProduct " + (i + 1) + ":\n" +
                    "Name: " + productName + "\n" +
                    "Brand: " + brand + "\n" +
                    "Ingredients: " + ingredients + "\n" +
                    "Nutrition Grade: " + nutritionGrade + "\n" +
                    "Per 100g:\n" +
                    "- Energy: " + energy + " kcal\n" +
                    "- Fat: " + fat + " g\n" +
                    "- Sugars: " + sugars + " g\n" +
                    "- Protein: " + protein + " g\n"
                );
            }


            numProducts = end;


            if (numProducts >= cachedProducts.length()) {
                mainInfo.add("\nNo more products to display. You've reached the end of the results.");
            }


        } catch (Exception e) {
            e.printStackTrace();
            mainInfo.add("Error retrieving data.");
        }
    }


    public ArrayList<String> getInfo() {
        return mainInfo;
    }
}
