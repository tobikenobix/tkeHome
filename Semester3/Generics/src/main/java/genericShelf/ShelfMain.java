package genericShelf;

public class ShelfMain {
    public static void printShelf(Shelf<?> s){
        for(ShelfItem sh : s){
            System.out.println(sh);
        }
    }
    public static void main(String[] args){

        Book insel = new Book("Java ist auch eine Insel", "Ullenbloom", 1246);
        Book schuld = new Book("Schuld", "Schirach", 208);
        Book bibiUndTina = new Book("Bibi und Tina", "Börnstädt", 34);
        Shelf<Book> bookShelf = new Shelf<>();
        bookShelf.set(0, insel);
        bookShelf.set(1, schuld);
        bookShelf.set(3, bibiUndTina);
        printShelf(bookShelf);

        Tool schrzieher = new Tool("Schraubenzieher");
        Tool saege = new Tool("Säge");
        Shelf<Tool> toolShelf = new Shelf<>();
        toolShelf.set(0, schrzieher);
        toolShelf.set(3, saege);
        printShelf(toolShelf);

        Shelf<Book> newBookShelf = new Shelf<>();
        newBookShelf.takeFrom(bookShelf);
        printShelf(newBookShelf);

        Shelf<ShelfItem> generalShelf = new Shelf<>();
        generalShelf.takeFrom(newBookShelf);
        printShelf(generalShelf);

        Shelf.transferAndTrim(bookShelf,newBookShelf);
        System.out.println("After Transfer and Trim bookshelf");
        printShelf(newBookShelf);

        Shelf.transferAndTrim(bookShelf, generalShelf);
        System.out.println("After Transfer and Trim general shelf");
        printShelf(generalShelf);

    }
}
