import java.util.*;

public class Book {
    private String bookId;
    private String bookName;
    private double importPrice;
    private double exportPrice;
    private String title;
    private String author;
    private double interest;
    private int year;

    public Book() {}

    public Book(String bookId, String bookName, double importPrice, double exportPrice, String title, String author, int year) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.title = title;
        this.author = author;
        this.year = year;
        calInterest();
    }

    public void calInterest() {
        this.interest = this.exportPrice - this.importPrice;
    }

    public void inputData(Scanner scanner) {
        while (true) {
            System.out.print("Nhập mã sách (Bxxxx): ");
            this.bookId = scanner.nextLine();
            if (this.bookId.matches("B\\d{4}")) break;
            System.out.println("Mã sách không hợp lệ! Phải có dạng Bxxxx.");
        }

        while (true) {
            System.out.print("Nhập tên sách (6-100 ký tự): ");
            this.bookName = scanner.nextLine();
            if (this.bookName.length() >= 6 && this.bookName.length() <= 100) break;
            System.out.println("Tên sách không hợp lệ! Phải từ 6 đến 100 ký tự.");
        }

        while (true) {
            System.out.print("Nhập giá nhập (>0): ");
            this.importPrice = Double.parseDouble(scanner.nextLine());
            if (this.importPrice > 0) break;
            System.out.println("Giá nhập phải lớn hơn 0!");
        }

        while (true) {
            System.out.print("Nhập giá bán (tối thiểu 10% cao hơn giá nhập): ");
            this.exportPrice = Double.parseDouble(scanner.nextLine());
            if (this.exportPrice >= this.importPrice * 1.1) break;
            System.out.println("Giá bán phải lớn hơn giá nhập ít nhất 10%!");
        }

        while (true) {
            System.out.print("Nhập tiêu đề sách: ");
            this.title = scanner.nextLine();
            if (!this.title.trim().isEmpty()) break;
            System.out.println("Tiêu đề không được để trống!");
        }

        while (true) {
            System.out.print("Nhập tác giả: ");
            this.author = scanner.nextLine();
            if (!this.author.trim().isEmpty()) break;
            System.out.println("Tác giả không được để trống!");
        }

        while (true) {
            System.out.print("Nhập năm xuất bản (sau 1970): ");
            this.year = Integer.parseInt(scanner.nextLine());
            if (this.year > 1970) break;
            System.out.println("Năm xuất bản phải sau năm 1970!");
        }

        calInterest();
    }

    public void displayData() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Book Name: " + bookName);
        System.out.println("Import Price: " + importPrice);
        System.out.println("Export Price: " + exportPrice);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Year: " + year);
        System.out.println("Interest: " + interest);
    }

    public String getBookId() { return bookId; }
    public String getBookName() { return bookName; }
    public double getInterest() { return interest; }
    public String getAuthor() { return author; }
    public double getExportPrice() { return exportPrice; }
    public void setExportPrice(double exportPrice) { this.exportPrice = exportPrice; calInterest(); }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Book> bookList = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n************ MENU ************");
            System.out.println("1. Danh sách sách");
            System.out.println("2. Thêm mới sách");
            System.out.println("3. Tính lợi nhuận của các sách");
            System.out.println("4. Cập nhật sách");
            System.out.println("5. Xóa sách");
            System.out.println("6. Sắp xếp sách theo lợi nhuận tăng dần");
            System.out.println("7. Tìm kiếm sách theo tác giả");
            System.out.println("8. Tìm kiếm sách theo khoảng giá bán");
            System.out.println("9. Thống kê sách theo mỗi tác giả");
            System.out.println("10. Thoát");
            System.out.print("Chọn chức năng: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    for (Book book : bookList) book.displayData();
                    break;
                case 2:
                    Book newBook = new Book();
                    newBook.inputData(scanner);
                    bookList.add(newBook);
                    break;
                case 3:
                    for (Book book : bookList) {
                        book.calInterest();
                        System.out.println("Lợi nhuận của sách " + book.getBookName() + ": " + book.getInterest());
                    }
                    break;
                case 4:
                    System.out.print("Nhập mã sách cần cập nhật: ");
                    String updateId = scanner.nextLine();
                    for (Book book : bookList) {
                        if (book.getBookId().equals(updateId)) {
                            book.inputData(scanner);
                            System.out.println("Cập nhật thành công!");
                            break;
                        }
                    }
                    break;
                case 5:
                    System.out.print("Nhập mã sách cần xóa: ");
                    String deleteId = scanner.nextLine();
                    bookList.removeIf(book -> book.getBookId().equals(deleteId));
                    System.out.println("Xóa thành công!");
                    break;
                case 6:
                    bookList.sort(Comparator.comparingDouble(Book::getInterest));
                    System.out.println("Sắp xếp thành công!");
                    break;
                case 7:
                    System.out.print("Nhập tác giả cần tìm: ");
                    String searchAuthor = scanner.nextLine();
                    for (Book book : bookList) {
                        if (book.getAuthor().equalsIgnoreCase(searchAuthor)) {
                            book.displayData();
                        }
                    }
                    break;
                case 8:
                    System.out.print("Nhập khoảng giá bán (min max): ");
                    double min = scanner.nextDouble();
                    double max = scanner.nextDouble();
                    scanner.nextLine();
                    for (Book book : bookList) {
                        if (book.getExportPrice() >= min && book.getExportPrice() <= max) {
                            book.displayData();
                        }
                    }
                    break;
                case 10:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Chức năng chưa được triển khai.");
            }
        } while (choice != 10);
        scanner.close();
    }
}
