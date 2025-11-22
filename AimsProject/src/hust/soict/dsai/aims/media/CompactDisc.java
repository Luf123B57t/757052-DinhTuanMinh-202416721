package hust.soict.dsai.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private ArrayList<Track> tracks = new ArrayList<>();

    public CompactDisc(String title, String category, float cost, String artist) {
        super();
        super.setTitle(title);
        super.setCategory(category);
        super.setCost(cost);
        this.artist = artist;
    }

    public String getArtist() {
        return artist;
    }

    public void addTrack(Track track) {
        if (tracks.contains(track)) {
            System.out.println("Track already exists: " + track.getTitle());
        } else {
            tracks.add(track);
            System.out.println("Track added: " + track.getTitle());
        }
    }

    public void removeTrack(Track track) {
        if (tracks.contains(track)) {
            tracks.remove(track);
            System.out.println("Track removed: " + track.getTitle());
        } else {
            System.out.println("Track not found: " + track.getTitle());
        }
    }

    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength(); // giả sử Track có getLength()
        }
        return totalLength;
    }

    public void play() {
        System.out.println("Compact Disc: " + this.getTitle());
        System.out.println("Artist: " + this.artist);
        System.out.println("Total length: " + this.getLength());

        for (Track t : tracks) {
            t.play();
        }
    }
    @Override
    public String toString() {
        String result = getTitle() + " - " + getCategory() + " - " + getCost() + " - Artist: " + artist + "\nTracks:\n";
        if (tracks.isEmpty()) {
            result += "No tracks\n";
        } else {
            for (Track t : tracks) {
                result += t + "\n"; //
            }
        }
        return result;
    }

}
