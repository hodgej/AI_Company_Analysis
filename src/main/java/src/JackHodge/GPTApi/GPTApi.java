package src.JackHodge.GPTApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


// Key: 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

/*
GPTApi: Interface with ChatGPT API.
 */
public class GPTApi {
    //Filepath of the prompt: default prompt.txt
    static String promptFilePath = "src/main/java/src/JackHodge/GPTApi/prompt.txt";

    // Returns the prompt is string form
    public static String getPrompt() throws IOException {
        return Files.lines(Paths.get(promptFilePath))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    // Generate a prompt based on provided context from a String Sentiment.
    public static String generatePrompt(String context) throws IOException {
        return String.format(getPrompt(), context);
    }

    // Get an AI Analysis based on context information.
    public static String gptResponse(String contextInformation) {
        // API Metadata
        String url = "https://api.openai.com/v1/chat/completions";
        String key = "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";
        String gpt_model = "gpt-3.5-turbo";

        try {
            // Establish Connection
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + key);
            connection.setRequestProperty("Content-Type", "application/json");

            // Body
            String completePrompt = generatePrompt(contextInformation);
            System.out.println("\n\n" + completePrompt);
            String body = "{\"model\": \"" + gpt_model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" +
                           completePrompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            System.out.println("GPT Response Complete.");

            // Clean Output [ get content key value ]
            String responseString = response.toString();
            System.out.println(responseString);
            int startIndex = responseString.indexOf("content")+10;
            int endIndex = responseString.indexOf("}", startIndex);
            return responseString.substring(startIndex, endIndex);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    // Converts hashmap sentiment map to a readable String
    // Phrases using [value] is a [key] format -- following the web_templates.json format.
    public static String convertSentimentToString(HashMap<String, String> sentimentMap){
        String paragraph = new String();
        System.out.println("Converting Sentiment HashMap to String in paragraph for AI");
        for(Map.Entry<String, String> item : sentimentMap.entrySet()){
            paragraph += "The " + item.getValue() + " is " + item.getKey() + ". ";
        }
        return paragraph;
    }

}
