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


public class GPTApi {
    static String promptFilePath = "src/main/java/src/JackHodge/GPTApi/prompt.txt";

    public static String getPrompt() throws IOException {
        return Files.lines(Paths.get(promptFilePath))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public static String generatePrompt(String context) throws IOException {
        return String.format(getPrompt(), context);
    }
    // Returns String JSON Response if successful, string "NULL" If an HTTP error occurs.
    public static String gptResponse(String contextInformation) {
        String url = "https://api.openai.com/v1/completions";
        String key = "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";
        String gpt_model = "gpt-3.5-turbo";

        try {
            URL url_obj = new URL(url);
            HttpURLConnection connection_obj = (HttpURLConnection) url_obj.openConnection();
            connection_obj.setRequestMethod("POST");

            connection_obj.setRequestProperty("Authorization", "Bearer " + key);
            connection_obj.setRequestProperty("Content-Type", "application/json");

            String completedPrompt = generatePrompt(contextInformation);
            System.out.println("------\n Sending Prompt: \n" + completedPrompt + "\n -------");
            String body = "{\"model\": \"" + gpt_model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + completedPrompt + "\"}]}";

            connection_obj.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection_obj.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            if(connection_obj.getResponseCode() ==HttpURLConnection.HTTP_OK) {
                // Response from ChatGPT
                BufferedReader br = new BufferedReader(new InputStreamReader(connection_obj.getInputStream()));
                String line;

                StringBuffer response = new StringBuffer();

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                // OUTPUT: Return the JSON Response
                return (response.toString());

            } else{
                    System.out.println("GPT Error: HTTP Response Code " + connection_obj.getResponseCode());
                    return "NULL"; // return error response NULL
                }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static String convertSentimentToString(HashMap<String, String> sentimentMap){
        String paragraph = new String();
        System.out.println("Converting Sentiment HashMap to String in paragraph for AI");
        for(Map.Entry<String, String> item : sentimentMap.entrySet()){
            paragraph += "The " + item.getValue() + " is " + item.getKey() + ". ";
        }
        return paragraph;
    }

}
