package hust.soict.dsai.aims;

import hust.soict.dsai.aims.media.*;
import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.store.Store;

import java.util.*;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        store.addMedia(new Book("The Hobbit", "Fantasy", 15.0f));
        store.addMedia(new Book("Effective Java", "Programming", 40.0f));
        store.addMedia(new Book("Calculus","Math", 15.0f, List.of("Jame", "Marry")));

        store.addMedia(new DigitalVideoDisc("Star Wars", "Sci-fi", "George Lucas",120 , 124));
        store.addMedia(new DigitalVideoDisc("The Matrix", "Sci-fi", "Lana", 136));

        CompactDisc cd1 = new CompactDisc("Adele - 30", "Music",1500.98f, "Adele");
        Track track1CD1 = new Track("All Night Parking (interlude)", 161);
        Track track2CD1 = new Track("To Be Loved", 403);
        Track track3CD1 = new Track("Woman Like Me", 300);
        cd1.addTrack(track1CD1);
        cd1.addTrack(track2CD1);
        cd1.addTrack(track3CD1);

        CompactDisc cd2 = new CompactDisc("The Gods We Can Touch", "Music",2000.22f, "Aurora");
        Track track1CD2 = new Track("Everything Matters", 180+34);
        Track track2CD2 = new Track("Blood in the Wine", 180+30);
        Track track3CD2 = new Track("Artemis", 60*2+39);
        cd2.addTrack(track1CD2);
        cd2.addTrack(track2CD2);
        cd2.addTrack(track3CD2);

        CompactDisc cd3 = new CompactDisc("Purpose", "Music",1000.98f, "Justin");
        Track track1CD3 = new Track("The Feeling", 4*60+5);
        Track track2CD3 = new Track("No Sense", 4*60+35);
        cd3.addTrack(track1CD3);
        cd3.addTrack(track2CD3);

        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(cd3);
        int choice;
        do {
            showMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    viewStore();
                    break;
                case 2:
                    updateStore();
                    break;
                case 3:
                    viewCart();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("AIMS:");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void viewStore() {
        store.printStore(); // assumes method to print all media
        int choice;
        do {
            storeMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    seeMediaDetails();
                    break;
                case 2:
                    addMediaToCart();
                    break;
                case 3:
                    playMedia();
                    break;
                case 4:
                    viewCart();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (true);
    }

    public static void storeMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }

    public static void mediaDetailsMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2");
    }

    public static void seeMediaDetails() {
        System.out.println("Enter media title:");
        String title = sc.nextLine();
        Media m = store.searchByTitle(title);
        if (m == null) {
            System.out.println("Media not found.");
            return;
        }
        System.out.println(m.toString());
        int choice;
        do {
            mediaDetailsMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    cart.addMedia(m);
                    System.out.println("Media added to cart.");
                    break;
                case 2:
                    if (m instanceof Playable) ((Playable)m).play();
                    else System.out.println("This media cannot be played.");
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (true);
    }

    public static void addMediaToCart() {
        System.out.println("Enter media title to add:");
        String title = sc.nextLine();
        Media m = store.searchByTitle(title);
        if (m == null) {
            System.out.println("Media not found.");
            return;
        }
        cart.addMedia(m);
        System.out.println("Added to cart. Current cart size: " + cart.getItems().size());
    }

    public static void playMedia() {
        System.out.println("Enter media title to play:");
        String title = sc.nextLine();
        Media m = store.searchByTitle(title);
        if (m == null) {
            System.out.println("Media not found.");
            return;
        }
        if (m instanceof Playable) ((Playable)m).play();
        else System.out.println("This media cannot be played.");
    }

    public static void updateStore() {
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                System.out.println("Select media type to add:");
                System.out.println("1. Book");
                System.out.println("2. Digital Video Disc");
                System.out.println("3. Compact Disc");
                int type = Integer.parseInt(sc.nextLine());
                System.out.println("Enter title:");
                String title = sc.nextLine();
                System.out.println("Enter category:");
                String category = sc.nextLine();
                System.out.println("Enter cost:");
                float cost = Float.parseFloat(sc.nextLine());

                Media media = null;
                switch(type) {
                    case 1: // Book
                        media = new Book(title, category, cost);
                        break;
                    case 2: // DVD
                        System.out.println("Enter director:");
                        String director = sc.nextLine();
                        System.out.println("Enter length:");
                        int length = Integer.parseInt(sc.nextLine());
                        media = new DigitalVideoDisc(title, category, director, length, cost);
                        break;
                    case 3: // CD
                        System.out.println("Enter artist:");
                        String artist = sc.nextLine();
                        media = new CompactDisc(title, category, cost, artist);
                        break;
                    default:
                        System.out.println("Invalid type.");
                }

                if (media != null) store.addMedia(media);
                break;
            case 2:
                System.out.println("Enter title to remove:");
                String t = sc.nextLine();
                Media m = store.searchByTitle(t);
                if (m != null) store.removeMedia(m);
                else System.out.println("Media not found.");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }


    public static void viewCart() {
        cart.printCart(); // assumes method to print cart details
        int choice;
        do {
            cartMenu();
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Filter by:");
                    System.out.println("1. ID");
                    System.out.println("2. Title");
                    int f = Integer.parseInt(sc.nextLine());
                    if (f == 1) {
                        System.out.println("Enter id:");
                        int id = Integer.parseInt(sc.nextLine());
                        cart.getItems().stream()
                                .filter(m -> m.getId() == id)
                                .forEach(System.out::println);
                    } else if (f == 2) {
                        System.out.println("Enter title:");
                        String title = sc.nextLine();
                        cart.getItems().stream()
                                .filter(m -> m.getTitle().equalsIgnoreCase(title))
                                .forEach(System.out::println);
                    }
                    break;

                case 2:
                    System.out.println("Sort by:");
                    System.out.println("1. Cost → Title");
                    System.out.println("2. Title → Cost");
                    int s = Integer.parseInt(sc.nextLine());
                    if (s == 1) {
                        cart.getItems().sort(new MediaComparatorByCostTitle());
                    } else if (s == 2) {
                        cart.getItems().sort(new MediaComparatorByTitleCost());
                    }
                    System.out.println("Sorted:");
                    cart.getItems().forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter title to remove: ");
                    String removeTitle = sc.nextLine();
                    for (Media m : cart.getItems()) {
                        if (m.getTitle().equals(removeTitle)) {
                            cart.removeMedia(m);
                            break;
                        }
                    }
                    break;
                case 4:
                    System.out.print("Enter title to play: ");
                    String playTitle = sc.nextLine();
                    Media mediaToPlay = null;
                    for (Media m : cart.getItems()) {
                        if (m.getTitle().equalsIgnoreCase(playTitle)) {
                            mediaToPlay = m;
                            break;
                        }
                    }
                    if (mediaToPlay == null) {
                        System.out.println("Media not found in cart.");
                    } else if (mediaToPlay instanceof Playable) {
                        ((Playable) mediaToPlay).play();
                    } else {
                        System.out.println("This media cannot be played.");
                    }
                    break;

                case 5:
                    System.out.println("Order placed. Cart cleared.");
                    cart.clear();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (true);
    }

    public static void cartMenu() {
        System.out.println("Options:");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }
}
