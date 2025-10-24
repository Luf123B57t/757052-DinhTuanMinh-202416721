public class Store {
    public static final int MAX_ITEMS = 100;
    private DigitalVideoDisc itemsInStore[] = new DigitalVideoDisc[MAX_ITEMS];
    private int qtyInStore = 0;

    public void addDVD(DigitalVideoDisc disc) {
        if (qtyInStore >= MAX_ITEMS) {
            System.out.println("Store is full");
        } else {
            itemsInStore[qtyInStore] = disc;
            qtyInStore++;
            System.out.println("Added: " + disc.getTitle());
        }
    }

    public void removeDVD(DigitalVideoDisc disc) {
        int idx = -1;
        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] == disc) {
                idx = i;
                break;
            }
        }
        if (idx != -1) {
            for (int i = idx; i < qtyInStore - 1; i++) {
                itemsInStore[i] = itemsInStore[i + 1];
            }
            itemsInStore[qtyInStore - 1] = null;
            qtyInStore--;
            System.out.println("Removed: " + disc.getTitle());
        } else {
            System.out.println("Cannot find this DVD in store");
        }
    }

    public void printStore() {
        System.out.println("***********************STORE***********************");
        for (int i = 0; i < qtyInStore; i++) {
            System.out.println((i + 1) + ". " + itemsInStore[i].toString());
        }
        System.out.println("***************************************************");
    }
}
