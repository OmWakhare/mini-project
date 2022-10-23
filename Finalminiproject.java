import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

import java.sql.Statement;

import java.util.ArrayList;

import java.util.Scanner;

class Book {

int id, qty; String title, author;

public Book(int number, int qty, String title, String author) {

this.id = number;

this.qty = qty;

this.title = title;

this.author = author;

}

@Override public String toString() {

return "BOOK DETAILS:- [ id: " + id + "\tTitle: " + title + "\tAuthor: " + author + "\tQty: " + qty + " ]\n"; }

}

public class BookS

{

private ArrayList bookList = new ArrayList<>();

void addNewBook (int id, int qty, String title, String author) {

bookList.add(new Book(id, qty, title, author));

}

void removeBook(int id) {

Book b = searchBook(id);

if(b != null) {

bookList.remove(b);

}

}

Book searchBook(int id) {

for(Book b: bookList) {

if(b.id == id) {

return b;

}

}

return null;

}

void updateCurrentBook(int id, String title, String author, int qty) {

if (bookList.isEmpty()) {

return;

}

Book b = bookList.get(id); b.title = title; b.author = author; b.qty = qty;

}

static void showMenu()

{

System.out.println("1. Enter book.");

System.out.println("2. Update book.");

System.out.println("3. Delete book.");

System.out.println("4. Search books.");

System.out.println("5. Exit.");

System.out.print("---- CHOOSE YOUR OPTION ----: ");

}

/**
 * @param args
 */
public static void main(String[] args)

{

try (

// Step 1:  database connection object

Connection conn = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/bookshop" , "root" , "123456");

// Step 2:  'Statement' object in the Connection

Statement stmt = conn.createStatement(); )

{

final BookS store = new BookS();

System.out.println("------- BOOK STORE ---------");

Scanner in = new Scanner(System.in);

showMenu(); int number, qty;

String title, author;

int choice = Integer.parseInt(in.nextLine());

while (choice != 6) {

switch (choice) {

case 1:

System.out.print("ENTER BOOK ID: ");

number = Integer.parseInt(in.nextLine());

String Insert1 = "insert into books " + "values " + number;

System.out.println("The SQL query is: " + Insert1);

int countInserted = stmt.executeUpdate (Insert1);

System.out.println(countInserted + " records inserted.\n");

System.out.print("ENTER BOOK TITLE: ");

title = in.nextLine();

String Insert2 = "insert into books " + "values " + title;

System.out.println ("The SQL query is: " + Insert1);

int countInserted1 = stmt.executeUpdate (Insert2);

System.out.println(countInserted1 + " records inserted.\n");

System.out.print("ENTER BOOK AUTHOR: ");

author = in.nextLine();

String Insert3 = "insert into books " + "values " + author;

System.out.println ("The SQL query is: " + Insert3);

int countInserted2 = stmt.executeUpdate (Insert3);

System.out.println(countInserted2 + " records inserted.\n");

System.out.print("ENTER BOOK QUANTITY: ");

qty = Integer.parseInt(in.nextLine());

String Insert4 = "insert into books " + "values " + qty;

System.out.println ("The SQL query is: " + Insert1);

int countInserted4 = stmt.executeUpdate (Insert4);

System.out.println(countInserted4 + " records inserted.\n");

store.addNewBook(number, qty, title, author); System.out.print(" ******* BOOK DETAILS ADDED ******** \n");

break;

//Search book to update book details

case 2:

System.out.print("ENTER BOOK ID: ");

number = Integer.parseInt(in.nextLine());

Book b = store.searchBook(number);

if(b == null) {

System.out.println("****** NO SUCH BOOKS ******.");

} else {

System.out.print("ENTER NEW BOOK TITLE: ");

title = in.nextLine();

String update1 = "update books set title = 'title' where id =" + title;

System.out.println ("The SQL query is: " + update1);

int countUpdat1 = stmt.executeUpdate (update1);

System.out.println(countUpdat1 + " records inserted.\n");

System.out.print("ENTER NEW BOOK AUTHOR: ");

author = in.nextLine();

String update2 = "update books set author = 'title' where id =" + author;

System.out.println ("The SQL query is: " + update2);

int countUpdat2 = stmt.executeUpdate (update2);

System.out.println(countUpdat2 + " records inserted.\n");

System.out.print("ENTER NEW BOOK QUANTITY: ");

qty = in.nextInt();

String update3 = "update books set title = 'qty' where id =" + qty;

System.out.println ("The SQL query is: " + update3);

int countUpdat3 = stmt.executeUpdate (update3);

System.out.println(countUpdat3 + " records inserted.\n");

store.updateCurrentBook(number, title, author, qty);

}

break;

case 3:

System.out.print("ENTER BOOK ID: ");

number = Integer.parseInt(in.nextLine());

String sqlDelete = "delete from books where id =" + number ;

System.out.println("The SQL query is: " + number);

 String countDeleted = stmt.executeUpdate (number);

System.out.println(countDeleted + " records deleted.\n" );

store.removeBook(number);

System.out.print(" ********* BOOK DELETED ********\n ");

break;

case 4:

System.out.print("ENTER BOOK ID: ");

number = Integer.parseInt(in.nextLine());

System.out.print("ENTER BOOK TITLE: ");

String searchTitle = in.next();;

String strSelect = "select * from books where Title = seacrhTitle";

b = store.searchBook(number);

if(b == null) {

System.out.println("******* NO SUCH BOOKS *******.");

} else {

System.out.println(b);

}

break;

case 5:

break;

}

showMenu();

choice = Integer.parseInt(in.nextLine());

}

in.close();

} catch (SQLException ex) {

ex.printStackTrace();

}

}
}
