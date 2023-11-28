package src.JackHodge.GPTApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


// Key: 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


public class GPTApi {
    public static String gptResponse(String prompt) {
        String url = "https://api.openai.com/v1/completions";
        String key = "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";
        String gpt_model = "gpt-3.5-turbo";

        try {
            URL url_obj = new URL(url);
            HttpURLConnection connection_obj = (HttpURLConnection) url_obj.openConnection();
            connection_obj.setRequestMethod("POST");

            connection_obj.setRequestProperty("Authorization", "Bearer " + key);
            connection_obj.setRequestProperty("Content-Type", "application/json");
            String body = "{\"model\": \"" + gpt_model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";

            connection_obj.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection_obj.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();


            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection_obj.getInputStream()));
            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // Return the JSON Response
            return (response.toString());


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
