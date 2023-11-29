package src.JackHodge.WebAPI;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class WebAPI {

    private String company_name;
    private String company_StockSymbol;

    public WebAPI(String company_name_, String company_symbol_){
        company_name = company_name_;
        company_StockSymbol = company_symbol_;
    }

    public ArrayList<String> generate_company_sentiment() {
        ArrayList<String> comDataArr = new ArrayList<String>();

        File webTemplates = new File("src/main/java/src/JackHodge/WebAPI/web_templates.json");
        Map<String, Object> templatesMap;

        try {
            templatesMap = new ObjectMapper().readValue(webTemplates, HashMap.class);

            // iterate map using entrySet to make an iterable collection
            for(Map.Entry<String, Object> entry: templatesMap.entrySet()){
                // web url; key for values
                String json_webUrl = entry.getKey();
                // get value data
                LinkedHashMap<String, Object> values = (LinkedHashMap<String, Object>) entry.getValue();
                // go through the url's data

                // URL CONFIGURATION SETTINGS //
                // Decide whether to insert Company Name or Stock Symbol
                // based on integer "data_insertion_type" in web_templates.json.
                // 0 = company_name, 1 = stock symbol
                Integer json_dataInsertionType = (Integer) values.get("data_insertion_type");
                // The String we need to insert
                String data_insertion = (json_dataInsertionType == 0 ? company_name : company_StockSymbol);

                // Completed URL of page to scrape
                String scraping_url = json_webUrl + String.format((String) values.get("url_template"), data_insertion);
                System.out.println("Scraping URL: " + scraping_url);

                // Begin Scraping Elements, insert in results array containing context+data
                // Format in json: element to scrape : context for AI

            }

        } catch (IOException e){
            throw new RuntimeException(e);
        }



        return comDataArr;
    }
}
