package src.JackHodge;

import src.JackHodge.app.JHodgeCompanyAnalysis;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class index {
    private JLabel item;
    private JPanel jp;
    private JTextField companyName;
    private JTextField companySymbol;
    private JButton analyzeButton;

    public index() {
        analyzeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cName = companyName.getText();
                String cSymbol = companySymbol.getText();
                String[] data = new String[]{cName, cSymbol};
                String returnedData;
                try {
                    returnedData = JHodgeCompanyAnalysis.companyAnalysis(data);
                } catch (IOException ex) {
                    returnedData = "Error";
                }

                results r = new results(returnedData);


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("index");
        frame.setContentPane(new index().jp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
