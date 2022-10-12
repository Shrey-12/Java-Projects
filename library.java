import java.util.Scanner;

public class library {
    static class Book {
        private int bookId;
        private String bookTitle;
        private int yearOfPublication;
        private String authorName;
        private String publisherName;
        private int numberOfAvailableCopies;
        private int totalCopies;

        //constructors
        public Book() {
            bookId=-1;
        }


        public void setDetails(int id, String title, int year, String author, String publisher, int count) {
            this.bookId = id;
            this.bookTitle = title;
            this.yearOfPublication = year;
            this.authorName = author;
            this.publisherName = publisher;
            this.totalCopies = count;
            this.numberOfAvailableCopies = count;
        }

        public void getDetails(int id) {
            if (bookId == id) {
                System.out.println("Book id: "+this.bookId);
                System.out.println("Book title: "+this.bookTitle);
                System.out.println("Year of Publication"+this.yearOfPublication);
                System.out.println("author Name: "+this.authorName);
                System.out.println("Publisher Name"+this.publisherName);
                System.out.println("Number of Available Copies: "+this.numberOfAvailableCopies+"~"+"Number of Total copies: "+this.totalCopies);
            }
        }

        public void issue(int id) {
            if (bookId == id) {
                if (numberOfAvailableCopies > 0) {
                    numberOfAvailableCopies--;
                } else {
                    System.out.println("Copies Exhausted");
                }
            }
            else
                System.out.println("Book not found!");
        }

        public void returnBook(int id) {
            if (bookId == id) {
                if (numberOfAvailableCopies < totalCopies) {
                    numberOfAvailableCopies++;
                } else {
                    System.out.println("Trying to return without issuing");
                }
            }
        }

        public int getId() {
            return bookId;
        }

        static void Update(Book[] A, int id) {
            Scanner input=new Scanner(System.in);
            int i;
            for (i = 0; i < A.length; i++) {
                if (A[i].getId() == id) {
                    System.out.println("book is already present in library!");
                }
                if (A[i].getId() == -1) {
                    break;
                }
            }
            A[i] = new Book();
            System.out.println("Enter Book Name: ");
            String title = input.nextLine();
            System.out.println("Enter Book year:");
            int year = input.nextInt();
            input.nextLine();
            System.out.println("Enter author Name:");
            String author = input.nextLine();
            System.out.println("Enter publisher Name:");
            String publisher = input.nextLine();
            System.out.println("Enter no. of books:");
            int count = input.nextInt();
            A[i].setDetails(id, title, year, author, publisher, count);
        }

        static void issueBook(Book[] A, int id) {
            for (Book book : A) {
                if (book.getId() == id) {
                    book.issue(id);
                    return;
                }
            }
            System.out.println("Book not in the library");
        }

        static void returnBook(Book[] A, int id) {
            for (Book book : A) {
                if (book.getId() == id) {
                    book.returnBook(id);
                    return;
                }
            }
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

        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            Book[] books = new Book[5];
            for (int i = 0; i < books.length; i++) {
                books[i] = new Book();
            }

            int k=1;
            while (k!=5) {
                System.out.print("----------------LIBRARY---------------");
                System.out.print("\n\n1. Set Details\n2. Get Details\n3. Issue \n4. Return\n5. Exit\n\n");
                System.out.println();

                k = input.nextInt();
                int id;


                switch (k) {
                    case 1 -> {
                        System.out.println("Enter Book id:");
                        id = input.nextInt();
                        Update(books, id);
                    }
                    case 2 -> {
                        System.out.println("Enter Book id:");
                        id = input.nextInt();
                        getBookDetails(books, id);
                    }
                    case 3 -> {
                        System.out.println("Enter Book id:");
                        id = input.nextInt();
                        issueBook(books, id);
                    }
                    case 4 -> {
                        System.out.println("Enter Book id:");
                        id = input.nextInt();
                        returnBook(books, id);
                    }
                    default -> {
                    }
                }
            }
            input.close();

        }
    }
}
