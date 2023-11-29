import java.util.ArrayList;
import java.util.Scanner;

class Item {
    private String title;
    private int year;
    private boolean available;

    public Item(String title, int year) {
        this.title = title;
        this.year = year;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Year: " + year);
        System.out.println("Available: " + (available ? "Yes" : "No"));
    }
}

class Book extends Item {
    private String author;

    public Book(String title, String author, int year) {
        super(title, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Author: " + author);
    }
}

class Library {
    private ArrayList<Item> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void displayAllItems() {
        for (Item item : items) {
            item.displayInfo();
            System.out.println();
        }
    }

    public Item searchItemByTitle(String title) {
        for (Item item : items) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                return item;
            }
        }
        return null;
    }

    public void borrowItem(String title) {
        Item item = searchItemByTitle(title);
        if (item != null && item.isAvailable()) {
            item.setAvailable(false);
            System.out.println("Item '" + title + "' has been borrowed.");
        } else if (item != null && !item.isAvailable()) {
            System.out.println("Item '" + title + "' is not available for borrowing.");
        } else {
            System.out.println("Item '" + title + "' not found in the library.");
        }
    }

    public void returnItem(String title) {
        Item item = searchItemByTitle(title);
        if (item != null && !item.isAvailable()) {
            item.setAvailable(true);
            System.out.println("Item '" + title + "' has been returned.");
        } else if (item != null && item.isAvailable()) {
            System.out.println("Item '" + title + "' is already available in the library.");
        } else {
            System.out.println("Item '" + title + "' not found in the library.");
        }
    }
}

class LibraryApp {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System Menu:");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Items");
            System.out.println("3. Search Item by Title");
            System.out.println("4. Borrow Item");
            System.out.println("5. Return Item");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Book Title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter Publication Year: ");
                    int bookYear = scanner.nextInt();

                    Book newBook = new Book(bookTitle, author, bookYear);
                    library.addItem(newBook);
                    System.out.println("Book added to the library.");
                    break;
                case 2:
                    System.out.println("All Items in the Library:");
                    library.displayAllItems();
                    break;
                case 3:
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Item Title to search: ");
                    String searchTitle = scanner.nextLine();
                    Item foundItem = library.searchItemByTitle(searchTitle);
                    if (foundItem != null) {
                        foundItem.displayInfo();
                    } else {
                        System.out.println("Item with Title '" + searchTitle + "' not found.");
                    }
                    break;
                case 4:
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Item Title to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowItem(borrowTitle);
                    break;
                case 5:
                    scanner.nextLine(); // Consume the newline character
                    System.out.print("Enter Item Title to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnItem(returnTitle);
                    break;
                case 6:
                    System.out.println("Exiting Library Management System.");
                    System.exit(0);
                    break; // Ensure to include break statement for case 6
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }
}