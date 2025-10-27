package hust.soict.dsai.lab01;
import java.util.Scanner;
public class Ex6_2 {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What's your name");
		String name = keyboard.nextLine();
		System.out.println("How old are you");
		int age = keyboard.nextInt();
		System.out.println("How high are you(m)");
		double height = keyboard.nextDouble();
		System.out.println("Mr/Mrs" + name + ", "+ age +" years old. "+ "Your height is "+ height + " m." );
	    keyboard.close();

	}

}