import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class Kalkulator extends JFrame {

    private JTextField textField;
    private StringBuilder input = new StringBuilder();

    public Kalkulator() {
        setTitle("Kalkulator");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(5, 5));

       
        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setFont(new Font("Arial", Font.BOLD, 20));
        add(textField, BorderLayout.NORTH);

      
        JPanel buttonPanel = new JPanel(new GridLayout(3, 6, 5, 5));
        String[] tombol = {
            "1", "2", "3", "4", "5", "6",
            "7", "8", "9", "0", "+", "-",
            "*", "/", "=", "%", "Mod", "Exit"
        };

        for (String label : tombol) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 14));
            buttonPanel.add(button);

            switch (label) {
                case "Exit":
                    button.addActionListener(e -> System.exit(0));
                    break;
                case "=":
                    button.addActionListener(e -> hitungHasil());
                    break;
                case "Mod":
                    button.addActionListener(e -> {
                        input.append("%");
                        textField.setText(input.toString());
                    });
                    break;
                default:
                    button.addActionListener(e -> {
                        input.append(label);
                        textField.setText(input.toString());
                    });
                    break;
            }
        }

        add(buttonPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void hitungHasil() {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            Object result = engine.eval(input.toString());
            textField.setText(String.valueOf(result));
            input.setLength(0);
        } catch (Exception e) {
            textField.setText("Error");
            input.setLength(0);
        }
    }

    public static void main(String[] args) {
        Kalkulator form = new Kalkulator();
    }
}
