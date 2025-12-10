package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.PlayerException;

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

    public void play() throws PlayerException {
        if(this.getLength() >0){
            System.out.println("Compact Disc: " + this.getTitle());
            System.out.println("Artist: " + this.artist);
            System.out.println("Total length: " + this.getLength());
            java.util.Iterator iter = tracks.iterator();
            Track nextTrack;
            while(iter.hasNext()){
                nextTrack = (Track) iter.next();
                try{
                    nextTrack.play();
                } catch (PlayerException e){
                    throw e;
                }
            }

        } else {
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }

    @Override
    public String getPlayInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playing CD: ").append(getTitle()).append("\n");
        sb.append("Artist: ").append(artist).append("\n");
        sb.append("Total length: ").append(getLength()).append("\n");
        if (tracks.isEmpty()) {
            sb.append("No tracks\n");
        } else {
            sb.append("Tracks:\n");
            for (Track t : tracks) {
                sb.append(" - ").append(t.getTitle()).append(" (").append(t.getLength()).append("s)\n");
            }
        }
        return sb.toString();
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
