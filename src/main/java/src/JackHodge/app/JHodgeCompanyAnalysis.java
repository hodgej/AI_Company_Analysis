package src.JackHodge.app;

import src.JackHodge.GPTApi.GPTApi;
import src.JackHodge.WebAPI.WebAPI;

import java.io.IOException;
import java.util.HashMap;

// Main Class for Company Analysis Package
public class JHodgeCompanyAnalysis {
    public static String companyAnalysis(String[] companyData) throws IOException {
        // Init WebAPI For scraping
        WebAPI myApi = new WebAPI(companyData[0], companyData[1]);
        // Return SentimentMap based on scraping
        HashMap<String, String> sentimentMap = myApi.generate_company_sentiment();
        // Convert map to a string via GPTApi Static Method
        String paragraphForm = GPTApi.convertSentimentToString(sentimentMap);
        // Return AI Response based on Paragraph form of sentiment
        String GPTResponse = GPTApi.gptResponse(paragraphForm);
        return GPTResponse;




    }
}


