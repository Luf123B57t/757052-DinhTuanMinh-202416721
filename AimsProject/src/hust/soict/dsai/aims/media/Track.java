package hust.soict.dsai.aims.media;

public class Track implements Playable {
    private String title;
    private int length;

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public Track(String title, int length) {
        this.title = title;
        this.length = length;
    }

    public void play() {
        System.out.println("Playing Track: " + this.title);
        System.out.println("Track length: " + this.length);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Track)) return false;
        Track t = (Track) o;
        if (this.title == null && t.title != null) return false;
        if (this.title != null && !this.title.equals(t.title)) return false;
        return this.length == t.length;
    }

    @Override
    public String toString() {
        return title + " - " + length;
    }


}
