package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {

    @Override
    public int compare(Media m1, Media m2) {
        // so sánh theo cost giảm dần
        int costCompare = Float.compare(m2.getCost(), m1.getCost());
        if (costCompare != 0) return costCompare;
        // nếu cost bằng nhau, so sánh theo title alphabet
        if (m1.getTitle() == null && m2.getTitle() == null) return 0;
        if (m1.getTitle() == null) return -1;//m1 đứng trước m2
        if (m2.getTitle() == null) return 1; //m1 đứng sau m2
        return m1.getTitle().compareTo(m2.getTitle());
    }
}
