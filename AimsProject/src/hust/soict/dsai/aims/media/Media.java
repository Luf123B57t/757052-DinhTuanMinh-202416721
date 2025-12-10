package hust.soict.dsai.aims.media;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public abstract class Media {
    private int id;
    private float cost;
    private String title;
    private String category;
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByCostTitle();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByTitleCost();

    public Media() {
    }

    public int getId() {
        return id;
    }

    public float getCost() {
        return cost;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Media)) return false;
        Media m = (Media) o;
        if (this.title == null) return m.title == null;
        return this.title.equals(m.title);
    }

}
