package hust.soict.dsai.aims.media;
import java.util.ArrayList;
import java.util.List;
public class Book extends Media {
    private List<String> authors = new ArrayList<String>();

    public Book(String title, String category, float cost) {
        super();
        setTitle(title);
        setCategory(category);
        setCost(cost);
    }

    public Book(String title, String category, float cost, List<String> authors) {
        this.authors = authors;
        setTitle(title);
        setCategory(category);
        setCost(cost);
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void addAuthor(String authorName) {
        if (authorName == null) return;
        if (authors.contains(authorName)) return;
        authors.add(authorName);
    }

    public void removeAuthor(String authorName) {
        if (authorName == null) return;
        if (!authors.contains(authorName)) return;
        authors.remove(authorName);
    }
    @Override
    public String toString() {
        String authorsStr = authors.isEmpty() ? "Unknown" : String.join(", ", authors);
        return getTitle() + " - " + getCategory() + " - " + getCost() + " - " + authorsStr;
    }


}
