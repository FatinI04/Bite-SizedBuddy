package com.example.GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OpeningPage{

    private JFrame frame;

    public OpeningPage() {
        WelcomePage w = new WelcomePage();
        w.disposeFrame2();

        // Create the main frame
        frame = new JFrame("Opening Screen");
        frame.setSize(550, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        Font titleLabelFont = new Font("serif", Font.PLAIN, 20);
        JLabel titleLabel = new JLabel("Enter the name of your virtual pet dog:", SwingConstants.CENTER);
        titleLabel.setFont(titleLabelFont);

        JTextField textInput = new JTextField(20); //input text
        textInput.setPreferredSize(new Dimension(110, 110));
        textInput.setMinimumSize(new Dimension(110, 110));
        textInput.setMaximumSize(new Dimension(110, 110));
        textInput.setHorizontalAlignment(JTextField.CENTER);
        Font textInputFont = new Font("serif", Font.PLAIN, 40);
        textInput.setFont(textInputFont);

        Font doneButtonFont = new Font("serif", Font.PLAIN, 40);
        JButton doneButton = new JButton("Done");//a button fetches data when pressed
        doneButton.setFont(doneButtonFont);
        

        // Layout setup
        JPanel panel = new JPanel(new GridLayout(3, 1)); //create the JPanel object
        panel.add(titleLabel);
        panel.add(textInput);
        panel.add(doneButton);

        //We have added components to our panel, then we add the PANEL to our FRAME
        frame.add(panel); 

        // Button behavior
        doneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pet = textInput.getText().trim(); 
                if (!pet.isEmpty()) {
                    frame.dispose();
                    MainPage m = new MainPage(pet);
                }
            }
        });

        frame.setVisible(true);
    }

    public void disposeFrame() {frame.dispose();}

}
