import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class BookProcessor {
    public static void main(String[] args) {
        try {
            String jsonContent = Files.readString(
                    Paths.get("src/students.json"),
                    StandardCharsets.UTF_8
            );

            ObjectMapper mapper = new ObjectMapper();
            List<Student> students = mapper.readValue(
                    jsonContent,
                    mapper.getTypeFactory().constructCollectionType(List.class, Student.class)
            );

            students.stream()
                    .peek(student -> System.out.println("Студент: " + student))
                    .flatMap(student -> student.getBooks().stream())
                    .sorted((book1, book2) -> Integer.compare(book1.getPages(), book2.getPages()))
                    .distinct()
                    .filter(book -> book.getYear() > 2000)
                    .limit(3)
                    .map(Book::getYear)
                    .findFirst()
                    .ifPresentOrElse(
                            year -> System.out.println("Год выпуска найденной книги: " + year),
                            () -> System.out.println("Книга не найдена")
                    );

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
            e.printStackTrace();
        }
    }
}