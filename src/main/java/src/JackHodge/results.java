package src.JackHodge;

import javax.swing.*;

public class results {
    JFrame frame = new JFrame("result");
    private JTextField textField;
    private JPanel result;

    public results(String r){

        frame.setContentPane(result);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(500,500);
        textField.setText(r);
        frame.setVisible(true);
    }

    public void addComponents(){

    }
}
