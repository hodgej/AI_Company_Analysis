package src.JackHodge;

import src.JackHodge.app.JHodgeCompanyAnalysis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/*

Main File: Contains the Main Method

 */


public class index {
    // Index Page GUI Elements
    private JLabel item;
    private JPanel jp;
    private JTextField companyName;
    private JTextField companySymbol;
    private JButton analyzeButton;
    private JLabel title;

    // GUI Class
    public index() {
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setLoading();
                analyzeButton.setText("Loading...");
                String cName = companyName.getText();
                String cSymbol = companySymbol.getText();
                String[] data = new String[]{cName, cSymbol};
                String returnedData;

                // RUN COMPANY ANALYSIS PACKAGE //

                try {
                    returnedData = JHodgeCompanyAnalysis.companyAnalysis(data);
                } catch (IOException ex) {
                    returnedData = "Error";
                }

                results r = new results(returnedData);


            }

            public void setLoading(){
                title.setText("Loading.");
            }
        });
    }

    // Main Method
    public static void main(String[] args) {
        // Init Frame
        JFrame frame = new JFrame("Company Analysis");
        frame.setContentPane(new index().jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
