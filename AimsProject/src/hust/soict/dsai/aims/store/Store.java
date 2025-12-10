package hust.soict.dsai.aims.store;

import java.util.ArrayList;
import hust.soict.dsai.aims.media.Media;

public class Store {
    private ArrayList<Media> itemsInStore = new ArrayList<>();

    public void addMedia(Media media) {
        if (media == null) return;
        if (itemsInStore.contains(media)) return;
        itemsInStore.add(media);
        System.out.println("Added to store: " + media.getTitle());
    }

    public void removeMedia(Media media) {
        if (media == null) return;
        if (!itemsInStore.contains(media)) {
            System.out.println("Cannot find this item in store");
            return;
        }
        itemsInStore.remove(media);
        System.out.println("Removed from store: " + media.getTitle());
    }

    public Media searchByTitle(String title) {
        if (title == null || title.trim().isEmpty()) return null;
        for (Media m : itemsInStore) {
            if (m.getTitle().equalsIgnoreCase(title.trim())) {
                return m;
            }
        }
        return null; // không tìm thấy
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        if (itemsInStore.isEmpty()) {
            System.out.println("Store is empty.");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                Media m = itemsInStore.get(i);
                System.out.println((i + 1) + ". " + m.getTitle() + " - $" + m.getCost());
            }
        }
        System.out.println("***************************************************");
    }
    public ArrayList<Media> getItemsInStore() {
        return itemsInStore;
    }

}
