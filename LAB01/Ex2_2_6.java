package LAB01;
import java.util.Scanner;
public class Ex2_2_6 {
	public static void first_degree(double a, double b) {
		if(a==0) {
			if(b==0) {
				System.out.println("Infinite solution");
			}
			System.out.println("No root");
		}
		double x = -b/a;
		System.out.println("The equation has 1 root: "+ x);
	}
	public static void first_degree_2_variables(double a11, double a12, double a21, double a22, double b1, double b2) {
		double D = a11*a22 - a12*a21;
		double D_x1 = a11*b2 - a21*b1;
		double D_x2 = a12*b2 - a22*b1;
		if(D != 0) {
		    double x1 = D_x1 / D;
		    double x2 = D_x2 / D;
		    System.out.println("x1 = " + x1);
		    System.out.println("x2 = " + x2);
		} else {
		    if(D_x1 == 0 && D_x2 == 0) {
		        System.out.println("Infinite solution");
		    } else {
		        System.out.println("No solution");
		    }
		}
	}
	public static void second_degree(double a, double b, double c) {
		if(a==0) {
			first_degree(b,c);
		}
		else {
			double delta = b*b-4*a*c;
			if(delta>0) {
				double x1 = (-b+Math.sqrt(delta))/(2*a);
				double x2 = (-b+Math.sqrt(delta))/(2*a);
				System.out.println("The equation has 2 roots: "+ x1 + " and"+x2);
				
			}
			if(delta==0) {
				double x = -b/(2*a);
				System.out.println("The equation has 1 root: "+x);
			}
			if(delta<0) {
				System.out.println("No solution");
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Choose equation type:");
		System.out.println("1. First degree (ax + b = 0)");
		System.out.println("2. Second degree (ax^2 + bx + c = 0)");
		System.out.println("3. System of 2 equations (a11x1 + a12x2 = b1, a21x1 + a22x2 = b2)");

		int choice = sc.nextInt();

		switch(choice) {
			case 1:
				System.out.print("Enter a: ");
				double a = sc.nextDouble();
				System.out.print("Enter b: ");
				double b = sc.nextDouble();
				first_degree(a, b);
				break;
			case 2:
				System.out.print("Enter a: ");
				double aa = sc.nextDouble();
				System.out.print("Enter b: ");
				double bb = sc.nextDouble();
				System.out.print("Enter c: ");
				double cc = sc.nextDouble();
				second_degree(aa, bb, cc);
				break;
			case 3:
				System.out.print("Enter a11: ");
				double a11 = sc.nextDouble();
				System.out.print("Enter a12: ");
				double a12 = sc.nextDouble();
				System.out.print("Enter b1: ");
				double b1 = sc.nextDouble();
				System.out.print("Enter a21: ");
				double a21 = sc.nextDouble();
				System.out.print("Enter a22: ");
				double a22 = sc.nextDouble();
				System.out.print("Enter b2: ");
				double b2 = sc.nextDouble();
				first_degree_2_variables(a11, a12, a21, a22, b1, b2);
				break;
			default:
				System.out.println("Invalid choice");
		}

	}

}
