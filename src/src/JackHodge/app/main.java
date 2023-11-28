package src.JackHodge.app;

import src.JackHodge.GPTApi.GPTApi;

public class main {
    // CHANGE METHOD NAME TO SOMETHING OTHER THAN MAIN
    public static void main(String[] args){
        System.out.println("Hello world");
        System.out.println("Home of the software which gets user input, scrapes the web, " +
                           "and feeds that web data into a pretrained AI Model");

        // Web scraping class -- format for holding that data (JSON?)
        // AI Model
        // GUI Output system

        System.out.println(GPTApi.gptResponse("Hello, how are you?"));

    }
}


