package src.JackHodge.WebAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/*
WebAPI: Class for web scraping
 */

/*
web_templates.json Documentation:

Each URL we scrape gets an object
 - Data insertion type : 1 if insert stock ticker, 0 if insert company name
 - url_template: url in which the data from data insertion type is inserted (company name/stock sym)
 - elements: all elements to be scraped (in full XPATH format)
     - Each element is a key associated with context to the AI
     - Thus the final format will be [element info] is [context for the ai]
     - No limit on number of elements you can scrape here.

 */
public class WebAPI {
    // Instanced with Company Details
    private String company_name;
    private String company_StockSymbol;

    public WebAPI(String company_name_, String company_symbol_){
        company_name = company_name_;
        company_StockSymbol = company_symbol_;
        System.out.println("Initialized New WebAPI for " + company_name);
    }

    // Scrapes the Web based on it's company and returns a sentiment hashmap.
    public HashMap<String, String> generate_company_sentiment() {
        // OUTPUT FORMAT: {Data extracted : AI Context (From the JSON File), ... }
        HashMap<String, String> sentimentMap = new HashMap<String, String>();

        // File from which metadata about each target location is located
        File webTemplates = new File("src/main/java/src/JackHodge/WebAPI/web_templates.json");
        // Map we load the json into
        Map<String, Object> templatesMap;
        // LOAD templatesMap //
        try {
            templatesMap = new ObjectMapper().readValue(webTemplates, HashMap.class);

            // iterate map using entrySet to make an iterable collection
            for(Map.Entry<String, Object> entry: templatesMap.entrySet()){
                // web url; key for values
                String json_webUrl = entry.getKey();
                System.out.println("Examining Website Key in JSON: " + json_webUrl);
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

                // WEB SCRAPING //

                // Begin Scraping Elements, insert in results array containing context+data
                // Format in json: element to scrape : context for AI
                Document web_page = Jsoup.connect(scraping_url)
                        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                        .get();

                // Map of all Elements : Context
                HashMap<String, String> elementSet = (HashMap<String, String>) values.get("elements");
                for(Map.Entry<String, String> element: elementSet.entrySet()) {
                    String currentHTMLElement = element.getKey();
                    String AIContext = element.getValue();
                    // Get Element based on XPATH
                    Elements gotElement = web_page.selectXpath(currentHTMLElement);
                    sentimentMap.put(gotElement.text(), AIContext);
                }
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }

        // Return the SentimentMap
        return sentimentMap;
    }
}
