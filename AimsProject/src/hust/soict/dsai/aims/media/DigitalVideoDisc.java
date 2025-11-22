package hust.soict.dsai.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {
    private String director;
    private int length;
    private static int nbDigitalVideoDisc = 0;

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }
    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super();
        nbDigitalVideoDisc++;
        super.setId(nbDigitalVideoDisc);
        super.setTitle(title);
        super.setCategory(category);
        super.setCost(cost);
        this.director = director;
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super();
        nbDigitalVideoDisc++;
        super.setId(nbDigitalVideoDisc);
        super.setTitle(title);
        super.setCategory(category);
        super.setCost(cost);
        this.director = director;
        this.length = length;
    }

    @Override
    public String toString() {
        return "DVD - "
                + super.getTitle() + " - "
                + super.getCategory() + " - "
                + director + " - "
                + length + "- "
                + super.getCost();
    }

    public void play() {
        System.out.println("Playing DVD: " + this.getTitle());
        System.out.println("DVD length: " + this.getLength());
    }
}
