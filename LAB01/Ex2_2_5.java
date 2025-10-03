package LAB01;
import java.util.Scanner;

public class Ex2_2_5 {
	private double sum(double num1, double num2) {
		return num1 + num2;
	}
	private double diff(double num1, double num2) {
		return Math.abs(num1 - num2);
	}
	private  double product(double num1, double num2) {
		return num1*num2;
	}
	private double division(double num1, double num2) {
	    if (num2 == 0) {
	        throw new ArithmeticException("Cannot divide by zero");
	    }
	    return num1 / num2;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double num1 = sc.nextDouble();
		double num2 = sc.nextDouble();
		Ex2_2_5 calc = new Ex2_2_5();
		System.out.println("Sum of two number: " + calc.sum(num1,num2));
		System.out.println("Difference of two number: "+  calc.diff(num1,num2));
		System.out.println("Product of two number : "+ calc.product(num1, num2));
		System.out.println("Division of two number: "+ calc.division(num1, num2));
		sc.close();

	}

}