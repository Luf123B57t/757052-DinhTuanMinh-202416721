public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("Onepiece", "Animation", 36.68f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Haikyuu", "Action", "Tommy", 59.95f);
        DigitalVideoDisc dvd6 = new DigitalVideoDisc("Dragon ball", "Children", 45.91f);
        DigitalVideoDisc dvd7 = new DigitalVideoDisc("Magic trick", "Fantasy", "John", 150, 92.1f);
        cart.addDigitalVideoDisc(dvd4);
        cart.addDigitalVideoDisc(dvd5);
        cart.addDigitalVideoDisc(dvd6);
        cart.addDigitalVideoDisc(dvd7);
        cart.printcart();
        cart.searchById(4);
        cart.searchByTitle("Onepiece");

    }
}
