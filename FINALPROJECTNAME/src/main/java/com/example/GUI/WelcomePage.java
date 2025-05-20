package com.example.GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;

public class WelcomePage {

    private JFrame frame;

    public WelcomePage() {
        // Create the main frame
        frame = new JFrame("Opening Screen");
        frame.setSize(550, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        //button
        Font nextButtonFont = new Font("serif", Font.PLAIN, 40);
        JButton nextButton = new JButton("Next");
        nextButton.setFont(nextButtonFont);

        //image
        //BufferedImage myPicture = ImageIO.read(new File(""));
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        

        // Layout setup
        JPanel panel = new JPanel(new GridLayout(2, 1)); //create the JPanel object
        //panel.add(picLabel);
        panel.add(nextButton);

        //We have added components to our panel, then we add the PANEL to our FRAME
        frame.add(panel); 

        // Button behavior
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                frame.dispose();
                OpeningPage o = new OpeningPage();;
            }
        });

        frame.setVisible(true);
    }

    public void disposeFrame2() {frame.dispose();}

}
