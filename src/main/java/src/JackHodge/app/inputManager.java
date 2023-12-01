package src.JackHodge.app;

import java.util.Scanner;

public class inputManager {
    public static String[] companyInput() {
        String[] output = new String[2];

        Scanner inputScan = new Scanner(System.in);
        System.out.println("Enter Company Name: ");
        output[0] = inputScan.nextLine();
        System.out.println("Enter Company Stock Symbol: ");
        output[1] = inputScan.nextLine();
        System.out.println("Running Analysis on " + output[0] + "(" + output[1] + ").");

        return output;
    }
}
