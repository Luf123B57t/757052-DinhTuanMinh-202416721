package hust.soict.dsai.lab01;
import java.util.Scanner;

public class Ex6_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // size of array
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter " + n + " numbers:");
        for(int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for(int i = 0; i < arr.length - 1; i++) {
            for(int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        // sorted array
        System.out.print("Sorted array: ");
        for(int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        double sum = 0;
        for(int i=0; i<n; i++) {
        	sum += arr[i];
        }
        double avg = sum/n;
        System.out.println("Sum of elements in array: "+ sum);
        System.out.println("Average element: "+ avg);
    }
}
