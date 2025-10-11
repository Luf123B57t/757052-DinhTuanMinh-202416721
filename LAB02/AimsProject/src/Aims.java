import java.util.Scanner;
public class Aims {
    public static void main(String[] args){
        Cart anOrder = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The lion king", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Onepiece", "Animation", 36.68f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Haikyuu", "Action", "Tommy", 59.95f);
        DigitalVideoDisc dvd6 = new DigitalVideoDisc("Dragon ball", "Children", 45.91f);
        DigitalVideoDisc dvd7 = new DigitalVideoDisc("Magic trick", "Fantasy", "John", 150, 92.1f);
        DigitalVideoDisc dvd8 = new DigitalVideoDisc("The hilarious dog", "Humor", 50.75f);
        DigitalVideoDisc dvd9 = new DigitalVideoDisc("Ghost hunter", "Horror", 20.86f);
        DigitalVideoDisc dvd10 = new DigitalVideoDisc("Time travel", "Science Fiction", 39.12f);
        DigitalVideoDisc[] store = {dvd1, dvd2, dvd3, dvd4, dvd5, dvd6, dvd7, dvd8, dvd9, dvd10};
        Scanner sc = new Scanner(System.in);
        int opt;
        while(true){
            System.out.println("Please enter an option (1-4)");
            System.out.println("Option 1: Add a DVD ");
            System.out.println("Option 2: Remove a DVD");
            System.out.println("Option 3: Calculate total cost");
            System.out.println("Option 4: Exit");
            opt  = sc.nextInt();
            if (opt == 1) {
                System.out.println("Available DVDs:");
                for (int i=0; i<store.length; i++) {
                    System.out.println((i + 1) + ". " + store[i].getTitle());
                }
                System.out.print("Select DVD number to add: ");
                int sel = sc.nextInt();
                if (sel >= 1 && sel <= store.length) {
                    anOrder.addDigitalVideoDisc(store[sel - 1]);
                    System.out.println("Added: " + store[sel - 1].getTitle());
                } else {
                    System.out.println("Invalid");
                }

            } else if (opt == 2) {
                System.out.println("Available DVDs:");
                for (int i=0; i<store.length; i++) {
                    System.out.println((i + 1) + ". " + store[i].getTitle());
                }
                System.out.print("Select DVD number to remove: ");
                int sel = sc.nextInt();
                if (sel >= 1 && sel <= store.length) {
                    anOrder.removeDigitalVideoDisc(store[sel - 1]);
                    System.out.println("Removed: " + store[sel - 1].getTitle());
                } else {
                    System.out.println("Invalid");
                }

            } else if (opt == 3) {
                System.out.println("Total cost: " + anOrder.totalCost());
            } else if (opt == 4) {
                System.out.println("Exiting");
                break;
            } else {
                System.out.println("Invalid option");
            }
        }

            }

        }

