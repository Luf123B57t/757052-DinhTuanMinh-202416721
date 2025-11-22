package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {

    @Override
    public int compare(Media m1, Media m2) {
        // so sánh theo title alphabet
        if (m1.getTitle() == null && m2.getTitle() == null) return 0;
        if (m1.getTitle() == null) return -1;
        if (m2.getTitle() == null) return 1;

        int titleCompare = m1.getTitle().compareTo(m2.getTitle());
        if (titleCompare != 0) return titleCompare;

        // nếu title bằng nhau, so sánh cost giảm dần
        return Float.compare(m2.getCost(), m1.getCost());
    }
}
