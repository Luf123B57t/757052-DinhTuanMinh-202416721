public class TestPassingParameter {
    public static void main(String[] args){
        DigitalVideoDisc jungleDVD = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc("Cinderella");
        swap(jungleDVD, cinderellaDVD);
        System.out.println("jungle dvd title:" + jungleDVD.getTitle());
        System.out.println("cinderella dvd titile: "+ cinderellaDVD.getTitle());
        changeTitle(jungleDVD, cinderellaDVD.getTitle());
        System.out.println("jungle dvd title: " + jungleDVD.getTitle());
        swap_real(jungleDVD, cinderellaDVD);
        System.out.println("jungle dvd title: "+jungleDVD.getTitle());

    }
    public static void swap(Object o1, Object o2){
        Object tmp = o1;
        o1 = o2;
        o2 = tmp;
    }
    public static void changeTitle(DigitalVideoDisc dvd, String title){
        String oldTitle = dvd.getTitle();
        dvd.setTitle(title);
        dvd = new DigitalVideoDisc(oldTitle);
    }

    public static void swap_real(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2){
        DigitalVideoDisc tmp = new DigitalVideoDisc(dvd1.getTitle());
        dvd1.setTitle(dvd2.getTitle());
        dvd2.setTitle(tmp.getTitle());
    }

    }

