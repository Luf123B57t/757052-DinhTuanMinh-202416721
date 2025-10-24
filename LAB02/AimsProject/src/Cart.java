public class Cart {
    public static final int MAX_NUMBERED_ORDERS = 20;
    private DigitalVideoDisc itemsordered[] = new DigitalVideoDisc[MAX_NUMBERED_ORDERS];
    private int qtyOrdered = 0;
    public void addDigitalVideoDisc(DigitalVideoDisc disc){
        if(qtyOrdered >= MAX_NUMBERED_ORDERS){
            System.out.println("The cart is full");
        }
        else{
            itemsordered[qtyOrdered] = disc;
            qtyOrdered +=1;
            System.out.println("The disc has been added to the cart");
            if (qtyOrdered == MAX_NUMBERED_ORDERS - 1){
                System.out.println("The cart is almost full, one more to add");
            }
        }
    }
    public void removeDigitalVideoDisc(DigitalVideoDisc disc){
        int idx = -1;
        for(int i=0; i<= MAX_NUMBERED_ORDERS; i++){
            if(itemsordered[i] == disc){
                idx = i;
                break;
            }
        }
        if(idx != -1){
            for(int i=idx; i<qtyOrdered-1; i++){
                itemsordered[i] = itemsordered[i+1];
            }
            itemsordered[qtyOrdered -1]=null;
            qtyOrdered -=1;
            System.out.println("The disc has been removed");
        }else{
            System.out.println("Cannot find the disc");
        }
    }
    public float totalCost(){
        float total = 0;
        for(int i=0; i< qtyOrdered; i++){
            total += itemsordered[i].getCost();
        }
        return total;
    }
    public void addDigitalVideoDisc(DigitalVideoDisc[] dvdList) {
        if(dvdList == null) return;
        int remaining = MAX_NUMBERED_ORDERS - qtyOrdered;
        if (remaining < dvdList.length) {
            System.out.println("Cannot add");
        } else {
            for(int i = 0; i < dvdList.length; i++){
                addDigitalVideoDisc(dvdList[i]);
            }
        }
    }
    /*
    public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
        if(dvdList == null) return;
        int remaining = MAX_NUMBERED_ORDERS - qtyOrdered;
        if (remaining < dvdList.length) {
            System.out.println("Cannot add");
        } else {
            for(int i = 0; i < dvdList.length; i++){
                addDigitalVideoDisc(dvdList[i]);
            }
        }
    }
    */
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2){
        int remaining = MAX_NUMBERED_ORDERS - qtyOrdered;
        if(remaining <2){
            System.out.println("Not enough place to add 2 dvds");
        }
        else{
            addDigitalVideoDisc(dvd1);
            addDigitalVideoDisc(dvd2);
        }
    }
}
