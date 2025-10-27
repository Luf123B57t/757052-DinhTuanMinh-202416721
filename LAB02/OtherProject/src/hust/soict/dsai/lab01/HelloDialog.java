package hust.soict.dsai.lab01;

import javax.swing.*;
public class HelloDialog {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String result;
		result =  JOptionPane.showInputDialog("Please enter your name");
		JOptionPane.showMessageDialog(null, "Hi "+ result+ " !");
		System.exit(0);

	}

}