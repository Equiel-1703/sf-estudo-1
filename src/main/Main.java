package main;

import data_structures.Tree;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class Main {
	private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private final static Dimension windowSize = new Dimension(640, 640);

	private static JPanel createExpressionField() {
		JPanel expressionFieldPanel = new JPanel();
		expressionFieldPanel.setLayout(new BoxLayout(expressionFieldPanel, BoxLayout.X_AXIS));
		expressionFieldPanel.setBackground(Color.WHITE);
		expressionFieldPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Creating the expression field
		JTextField expressionField = new JTextField();
		expressionField.setFont(new Font("Arial", Font.PLAIN, 20));
		expressionField.setBorder(new EtchedBorder(EtchedBorder.RAISED));
		
		// Creating the analise button
		JButton analiseButton = new JButton("Analise");
		analiseButton.setFont(new Font("Arial", Font.PLAIN, 12));
		analiseButton.setBackground(Color.WHITE);
		analiseButton.setMaximumSize(new Dimension((int)(analiseButton.getMaximumSize().getWidth()), 30));

		analiseButton.addActionListener(e -> {
			
		});

		// Adding the components to the panel
		expressionFieldPanel.add(expressionField);
		expressionFieldPanel.add(Box.createRigidArea(new Dimension(10, 0)));
		expressionFieldPanel.add(analiseButton);

		return expressionFieldPanel;
	}

	private static void createAndShowGUI() {
		JFrame frame = new JFrame("Java - Syntax Tree");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(windowSize);

		// Position the window in the center of the screen
		frame.setLocation((screenSize.width - windowSize.width) / 2, (screenSize.height - windowSize.height) / 2);

		// Setting the layout
		BorderLayout layout = new BorderLayout();
		layout.setVgap(0);
		frame.setLayout(layout);

		// Adding the expression field
		frame.add(createExpressionField(), BorderLayout.NORTH);

		// Test panel
		JPanel testPanel = new JPanel();
		testPanel.setBackground(Color.BLACK);
		frame.add(testPanel, BorderLayout.CENTER);

		// Adding parsed expression field
		JLabel parsedExpressionField = new JLabel();
		parsedExpressionField.setFont(new Font("Arial", Font.PLAIN, 20));
		parsedExpressionField.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder(EtchedBorder.RAISED)));
		parsedExpressionField.setHorizontalAlignment(SwingConstants.CENTER);
		parsedExpressionField.setText("Hello World");
		frame.add(parsedExpressionField, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}