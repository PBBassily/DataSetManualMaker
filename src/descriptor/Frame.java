package descriptor;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {

	int frameHeight = 500;
	int frameWidth = 500;

	public Frame() {

		super("Franky1000");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		ImageIcon image = new ImageIcon(Constants.DATASET_NAME+"/1.jpg");

		JLabel imageLabel = new JLabel(image);
		imageLabel.setBounds(frameWidth / 2 - image.getIconWidth() / 2, 0, image.getIconWidth(), image.getIconHeight());

		getContentPane().add(imageLabel);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(50, 350, 200, 20);
		textArea.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
 					System.out.println("ENTER pressed");
				}
			}
		});
		getContentPane().add(textArea);
		
		JButton btnRed = new JButton("red");
		btnRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText(textArea.getText() + "red ");
			}
		});
		btnRed.setBounds(50, 400, 89, 23);
		getContentPane().add(btnRed);

	}
}
