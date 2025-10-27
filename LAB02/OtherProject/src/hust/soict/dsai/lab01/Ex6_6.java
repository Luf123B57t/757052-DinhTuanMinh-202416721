package hust.soict.dsai.lab01;
import java.util.Scanner;

public class Ex6_6{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // size of matrices
        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        int[][] matrix1 = new int[rows][cols];
        int[][] matrix2 = new int[rows][cols];
        int[][] sum = new int[rows][cols];

        // first matrix
        System.out.println("Enter elements of first matrix:");
        for(int i=0; i < rows; i++) {
            for(int j=0; j < cols; j++) {
                matrix1[i][j] = sc.nextInt();
            }
        }

        // second matrix
        System.out.println("Enter elements of second matrix:");
        for(int i=0; i < rows; i++) {
            for(int j=0; j < cols; j++) {
                matrix2[i][j] = sc.nextInt();
            }
        }

        // add
        for(int i = 0; i < rows; i++) {
            for(int j=0; j < cols; j++) {
                sum[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        // result
        System.out.println("Sum of matrices:");
        for(int i=0; i < rows; i++) {
            for(int j=0; j < cols; j++) {
                System.out.print(sum[i][j] + " ");
            }
            System.out.println();
        }
    }
}