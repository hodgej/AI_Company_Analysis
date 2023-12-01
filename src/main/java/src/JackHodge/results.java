package src.JackHodge;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class results {
    JFrame frame = new JFrame("Results");
    private JPanel result = new JPanel(); // Initialize the result panel
    private JLabel title;

    public results(String r) {
        SwingUtilities.invokeLater(() -> {
            createUI(r);
        });
    }

    public void createUI(String r) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        ArrayList<String> lines = processText(r);

        result.setLayout(new GridLayout(0, 1)); // 1 column, as many rows as needed

        for (String line : lines) {
            JLabel l = new JLabel(line);
            result.add(l);
        }

        frame.setContentPane(result);
        frame.setVisible(true);
    }

    public ArrayList<String> processText(String text) {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder currentLine = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            currentLine.append(text.charAt(i));

            if ((i + 1) % 50 == 0) {
                // Add a line break after every 20 characters
                lines.add(currentLine.toString());
                currentLine = new StringBuilder();
            }
        }

        // Add the last line if it's not empty
        if (currentLine.length() > 0) {
            lines.add(currentLine.toString());
        }

        return lines;
    }

    public void addComponents() {
        // Add any additional components if needed
    }
}
