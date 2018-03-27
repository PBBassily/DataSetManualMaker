package descriptor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;

public class MainFrame extends JFrame {

	Engine engine;
	ImageIcon image;
	JLabel imageLabel;
	JTextArea textArea;
	String imageName;
	int frameHeight = 700;
	int frameWidth = 700;
	private JTextField[] textFields;
	private JLabel[] labels;
	private JLabel lblName;
	private JTextField gotoField;
	private JButton gotoButton;

	public MainFrame() {

		super("Franky1000");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		getContentPane().setLayout(null);
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		engine = new Engine();
		imageName = engine.getImageName();
		image = new ImageIcon("renamed_data/" + imageName);
		imageLabel = new JLabel(image);
		imageLabel.setBounds(frameWidth / 2 - 260, 25, 500, 500);
		getContentPane().add(imageLabel);
		
		gotoField = new JTextField();
		gotoField.setBounds(150, 5, 150, 25);
		getContentPane().add(gotoField);
		
		gotoButton = new JButton("go to");
		gotoButton.setBounds(300, 5, 150, 25);
		getContentPane().add(gotoButton);
		gotoButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				saveText();
				updateImage(Integer.parseInt(gotoField.getText()));
				textArea.setText("");
			}
		});
		

		textFields = new JTextField[10];
		labels = new JLabel[10];
		String[] shortcuts = { "this", "dog", "spots", "black", "white",
				"ears", "nose", "yellow", "orange", "brown" };
		for (int i = 0; i < textFields.length; i++) {
			labels[i] = new JLabel("" + (i + 1) % 10);
			labels[i].setFont(new Font("Tahoma", Font.BOLD, 11));
			labels[i].setBounds(63 + i * 53, 535, 14, 14);
			getContentPane().add(labels[i]);

			textFields[i] = new JTextField(shortcuts[i]);
			textFields[i].setColumns(10);
			textFields[i].setBounds(47 + i * 53, 552, 46, 20);
			getContentPane().add(textFields[i]);

		}


		textArea = new JTextArea();
		textArea.setBounds(47, 583, 580, 50);
		textArea.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_CONTROL) {
					saveText();
					updateImage();
					textArea.setText("");
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				super.keyReleased(arg0);
				int key = arg0.getKeyCode();

				if (KeyEvent.VK_0 <= key && key <= KeyEvent.VK_9) {
					textArea.setText(textArea.getText().replace(
							"" + (key - KeyEvent.VK_0), "")
							+ " "
							+ textFields[(key - KeyEvent.VK_0 + 9) % 10]
									.getText());
				}

			}

		});
		getContentPane().add(textArea);

		lblName = new JLabel(imageName);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblName.setBounds(10, 5, 125, 15);
		getContentPane().add(lblName);

	}

	private void updateImage() {
		String temp = imageName;
		imageName = engine.getNewImage();

		if (imageName == null) {
			imageName = temp;
			JOptionPane.showMessageDialog(new JFrame(), "The End");
			return;
		}

		image = new ImageIcon("renamed_data/" + imageName);
		imageLabel.setIcon(image);
		lblName.setText(imageName);
		repaint();

	}
	
	private void updateImage(int index) {
		String temp = imageName;
		imageName = engine.getNewImage(index);

		if (imageName == null) {
			imageName = temp;
			JOptionPane.showMessageDialog(new JFrame(), "The End");
			return;
		}

		image = new ImageIcon("renamed_data/" + imageName);
		imageLabel.setIcon(image);
		lblName.setText(imageName);
		repaint();

	}

	private void saveText() {

		String text = textArea.getText();
		// do not append dummy lines
		if (text.length() == 0)
			return;
		String path = "text/" + imageName.substring(0, imageName.indexOf("."))
				+ ".txt";
		File f = new File(path);

		if (f.exists()) {

			try {
				text += "\n";
				Files.write(Paths.get(path), text.getBytes(),
						StandardOpenOption.APPEND);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			PrintWriter writer;
			try {
				writer = new PrintWriter(path, "UTF-8");
				writer.println(text);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
}
