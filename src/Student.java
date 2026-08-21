import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String name;
    private final List<Book> books;

    @JsonCreator
    public Student(
            @JsonProperty("name") String name,
            @JsonProperty("books") List<Book> books) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (books == null || books.size() < 5) {
            throw new IllegalArgumentException("Student must have at least 5 books");
        }
        this.name = name;
        this.books = new ArrayList<>(books);
    }

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}