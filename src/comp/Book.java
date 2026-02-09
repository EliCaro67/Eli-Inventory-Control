package comp;

public class Book {
    private final Author author;
    private final String title;
    private boolean isAvailable;
    private final Genre genre;
    private final String isbn;
    private int count;


    public Book(Author author, String title, boolean isAvailable, Genre genre,
                String isbn) {
        this.author = author;
        this.title = title;
        this.isAvailable = isAvailable;
        this.genre = genre;
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getCount() {
        return count;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Genre getGenre() {
        return genre;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Title: ").append(title).append("\n");
        stringBuilder.append("Author: ").append(author.surname()).append("\n");
        stringBuilder.append("Genre: ").append(genre).append("\n");
        stringBuilder.append("---\n");
        return stringBuilder.toString();
    }
}
