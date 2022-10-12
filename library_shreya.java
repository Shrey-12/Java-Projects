import java.util.Scanner;

public class library_shreya {
    static Scanner input = new Scanner(System.in);

    static class Book {
        private int bookId;
        private String bookTitle;
        private int yearOfPublication;
        private String authorName;
        private String publisherName;
        private int numberOfAvailableCopies;
        private int totalCopies;

        public Book() {
        }

        public Book(int copies) {
            totalCopies = copies;
            bookId=-1;
        }

        public void setDetails(int id, String title, int year, String author, String publisher, int count) {
            bookId = id;
            bookTitle = title;
            yearOfPublication = year;
            authorName = author;
            publisherName = publisher;
            totalCopies = count;
            numberOfAvailableCopies = count;
        }

        public void getDetails(int id) {
            if (bookId == id) {
                System.out.println("Book id: "+this.bookId);
                System.out.println("Book title: "+this.bookTitle);
                System.out.println("Year of Publication"+this.yearOfPublication);
                System.out.println("author Name: "+this.authorName);
                System.out.println("Publisher Name"+this.publisherName);
                System.out.println("Number of Available Copies: "+this.numberOfAvailableCopies+"  "+"Number of Total copies: "+this.totalCopies);

            }

        }

        public void issue(int id) {
            if (bookId == id) {
                if (numberOfAvailableCopies > 0) {
                    numberOfAvailableCopies--;
                } else {
                    System.out.println("No more copies available for issuing");
                }

            }

        }

        public void returnBook(int id) {
            if (bookId == id) {
                if (numberOfAvailableCopies < totalCopies) {
                    numberOfAvailableCopies++;
                } else {
                    System.out.println("Book not issued from this library.");
                }

            }

        }

        public int getId() {
            return bookId;
        }

    }

    static void addBook(Book[] A, int id) {
        int i ;
        for (i = 0; i < A.length; i++) {
            if (A[i] != null && A[i].getId() == id) {
                System.out.println("Book already present in library!");
                return;
            }
            assert A[i] != null;
            if (A[i].getId() == -1) {
                break;
            }
        }

        input.nextLine();
        System.out.print("Enter Book Name:");
        String title = input.nextLine();
        System.out.println("Enter Book year:");
        int year = input.nextInt();
        input.nextLine();
        System.out.println("Enter author Name:");
        String author = input.nextLine();
        System.out.println("Enter publisher Name:");
        String publisher = input.nextLine();
        System.out.println("Enter no. of A:");
        int count = input.nextInt();
        input.nextLine();
        A[i] = new Book(count);
        A[i].setDetails(id, title, year, author, publisher, count);
    }

    static void issueBook(Book[] A, int id) {
        for (Book book : A) {
            if (book.getId() == id) {
                book.issue(id);
                return;
            }
        }
        System.out.println("Book not in library!");
    }

    static void getBookDetails(Book[] A, int id) {
        for (Book book : A) {
            if (book.getId() == id) {
                book.getDetails(id);
                return;
            }
        }
        System.out.println("Book not in library!");
    }

    static void returnBook(Book[] A, int id) {
        for (Book book : A) {
            if (book.getId() == id) {
                book.returnBook(id);
                return;
            }
        }
    }

    public static void main(String... args) {

        Book[] A = new Book[5];
        for (int i = 0; i < A.length; i++) {
            A[i] = new Book();
        }
        int k=1;
        int id;
        while (k!=5) {
            System.out.print(
                    "\n\n1. Set Details\n2. Get Details\n3. Issue \n4. Return\n5. Exit\n\n");
            k = input.nextInt();

            switch (k) {
                case 1 -> {
                    System.out.println("Enter Book id:");
                    id = input.nextInt();
                    addBook(A, id);
                }
                case 2 -> {
                    System.out.println("Enter Book id:");
                    id = input.nextInt();
                    getBookDetails(A, id);
                }
                case 3 -> {
                    System.out.println("Enter Book id:");
                    id = input.nextInt();
                    issueBook(A, id);
                }
                case 4 -> {
                    System.out.println("Enter Book id:");
                    id = input.nextInt();
                    returnBook(A, id);
                }
                default -> {
                }
            }
        }
        input.close();
    }

}

// T/N: why has scanner object been passed in functions