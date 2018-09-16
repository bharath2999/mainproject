
/*
 * Author:BHARATH,R
 * Date: 11/27/2017
 * StudentId: 999993704
 * Description: The Calculator class is used to create a simple calculator using java swing
 *              package. The class consists of 4 methods
 *              -constructor method where the calculator gui is built using the 
 *               jpanel, jbutton, border and grid layouts. Each button is added with a ActionListener.
 *              -actionperformed method is used to read the value on the buttons pressed on the calculator
 *               based on different conditions. The values pressed are displayed on the JTextField
 *              -calculate method is called from the actionperformed method when one of the following 
 *               buttons is pressed: '+', '-', '*', ,'/' and '=' on calculator gui.
 *              -main method uses JFrame to incorporate the panel created in the Calculator constructor class 
 *               and instantiates the Calculator GUI objecT.            
 */
//importing required java swing packages
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
//
public class Calculator extends JPanel implements ActionListener {

	// TextField object is created
	private JTextField total = new JTextField("0");
	private String operator = "=";
	private double result = 0;
	private boolean compute = true;

	public Calculator() {
		// adds border layout to the container of the JFrame
		setLayout(new BorderLayout());

		// sets the size of the text field
		total.setPreferredSize(new Dimension(200, 50));

		// set the background color of the text field
		total.setBackground(Color.LIGHT_GRAY);

		// makes the textfield editable
		total.setEditable(false);

		// adds the textfield to the container at the top
		add(total, "North");

		// creates JPanel object
		JPanel panel = new JPanel();

		// adds the grid layout to the JPanel of size 4 * 4
		panel.setLayout(new GridLayout(4, 4));

		// sets the panels background color
		panel.setBackground(new Color(149, 166, 193));

		// string that holds the labels for all the calculator buttons
		String buttonLabels = "789/456*123-0.=+";

		// loop to create and add the buttons with labels to the calculator with
		// actionListener to each button
		for (int k = 0; k < buttonLabels.length(); k++) {
			JButton b = new JButton(buttonLabels.substring(k, k + 1));
			panel.add(b);
			b.addActionListener(this);
		}

		// adds the panel to the container at the Center
		add(panel, "Center");

		// creates another JPanel object
		JPanel panel1 = new JPanel();

		// adds the grid layout to the JPanel of size 1 * 1
		panel1.setLayout(new GridLayout(1, 1));

		// create and add the clear button with label to the calculator with
		// actionListener
		JButton a = new JButton("c");
		panel1.add(a);
		a.addActionListener(this);
		panel1.setPreferredSize(new Dimension(100, 50));
		// adds the panel to the container at the bottom
		add(panel1, "South");

	}

	public void actionPerformed(ActionEvent evt) {

		// gets the string representing the action command which basically the
		// value
		// on the button pressed on calculator object
		String a = evt.getActionCommand();

		// checks if the value on the button pressed is between '0' and '9' or
		// '.' .
		// sets the textfield value based on the inner if condition
		if ('0' <= a.charAt(0) && a.charAt(0) <= '9' || a.equals(".")) {
			if (compute)
				total.setText(a);
			else {
				total.setText(total.getText() + a);
			}
			compute = false;
		}
		// checks if the value on the button pressed is c. resets the values of
		// the instance variables
		else if (a.equals("c")) {
			total.setText("0");
			result = 0;
			operator = "=";
			compute = true;
		}
		// checks if the value on the button pressed is other than 0 to 9, '.',c
		// and executes the following expressions
		else {
			if (compute) {
				// checks if the initial value is '-' and the value in the
				// textfield is 0
				if (a.equals("-") && (total.getText()).equals("0")) {
					total.setText(a);
					compute = false;
				} else
					operator = a;
			} else {
				// converts the string value to double
				double val1 = Double.parseDouble(total.getText());
				// calculate function is called
				calculate(val1);
				operator = a;
				compute = true;
			}
		}
	}

	private void calculate(double val2) {
		// if button pressed is '+' then addition operation is performed
		if (operator.equals("+"))
			result += val2;
		// if button pressed is '-' then subtraction operation is performed
		else if (operator.equals("-"))
			result -= val2;
		// if button pressed is '*' then multiplication operation is performed
		else if (operator.equals("*"))
			result *= val2;
		// if button pressed is '/' then division operation is performed
		else if (operator.equals("/"))
			result /= val2;
		// if button pressed is '=' then result is computed and displayed
		else if (operator.equals("="))
			result = val2;
		total.setText("" + result);
	}

	public static void main(String[] args) {
		//frame object is created
		JFrame iframe = new JFrame();
		//frame title is set to Calculator
		iframe.setTitle("Calculator");
		//frame size is set to the following dimension
		iframe.setSize(200, 300);
		//container object is created
		Container contentPane = iframe.getContentPane();
		//calculator object is created and added to the container
		contentPane.add(new Calculator());
		//frame is made visible
		iframe.setVisible(true);
	}
}