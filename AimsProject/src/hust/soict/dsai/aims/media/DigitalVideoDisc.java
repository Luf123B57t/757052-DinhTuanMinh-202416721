package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

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

    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }
    @Override
    public String getPlayInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playing DVD: ").append(getTitle()).append("\n");
        sb.append("Director: ").append(director).append("\n");
        sb.append("Length: ").append(length).append("\n");
        return sb.toString();
    }
}
