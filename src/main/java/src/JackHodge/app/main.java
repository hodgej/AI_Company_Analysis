package src.JackHodge.app;

import src.JackHodge.GPTApi.GPTApi;
import src.JackHodge.WebAPI.WebAPI;

import java.io.IOException;
import java.util.HashMap;

public class main {
    // CHANGE METHOD NAME TO SOMETHING OTHER THAN MAIN
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world");
        System.out.println("Home of the software which gets user input, scrapes the web, " +
                           "and feeds that web data into a pretrained AI Model");

        String[] companyData = inputManager.companyInput();

        WebAPI myApi = new WebAPI(companyData[0], companyData[1]);
        HashMap<String, String> sentimentMap = myApi.generate_company_sentiment();
        String temp_paragraphForm = GPTApi.convertSentimentToString(sentimentMap);
        // Method to convert company sentiment into paragraph form.
        System.out.println(temp_paragraphForm);
        System.out.println(GPTApi.gptResponse("Hello, how are you?"));

        System.out.println(GPTApi.getPrompt());
    }
}


