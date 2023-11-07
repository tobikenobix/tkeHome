package genericShelf;

public class Book  extends ShelfItem{
    private final String title;
    private final String author;
    private final int pages;

    public Book(String title, String author, int pages){
        if(title.isEmpty() || title.equals(" ") || author.isEmpty() || author.equals(" ") || pages<=0)
            throw new IllegalArgumentException("Illegal Arguments passed");
        this.title = title;
        this.author = author;
        this.pages = pages;

    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor(){
        return this.author;
    }

    public int getPages(){
        return this.pages;
    }

    @Override
    public String toString(){
        return String.format("Title: %s. Author: %s. Pages: %d", this.title, this.author, this.pages);

    }
}
