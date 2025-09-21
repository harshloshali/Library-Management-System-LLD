package org.assignment;

import org.assignment.entities.Book;
import org.assignment.entities.Branch;
import org.assignment.entities.Patron;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Book myBook = new Book.Builder("ISBN-GOOSEBUMPS-001").
                title("Goosebumps")
                .author("Harsh Loshali")
                .publicationYear(2020)
                .build();

        System.out.println(myBook.toString());

        LibrarySystem system = new LibrarySystem();

        Branch b1 = LibraryFactory.createBranch("B01", "Mumbai Branch");
        Branch b2 = LibraryFactory.createBranch("B02", "Bangalore Branch");
        system.addBranch(b1);
        system.addBranch(b2);
        Book myBook2 = new Book.Builder("ISBN-TWOSTATES-001").
                title("Two States")
                .author("Chetan Bhagat")
                .publicationYear(2014)
                .build();



        system.registerBook(myBook);
        system.registerBook(myBook2);

        system.addBookToBranch("B01", myBook.getIsbn(), 2);
        system.addBookToBranch("B01", myBook2.getIsbn(), 1);
        system.addBookToBranch("B02", myBook.getIsbn(), 1);

        Patron rohan = LibraryFactory.createPatron("P001", "Rohan");
        Patron raju   = LibraryFactory.createPatron("P002", "Raju");
        Patron baburao = LibraryFactory.createPatron("P003", "Baburao");
        system.addPatron(rohan);
        system.addPatron(raju);
        system.addPatron(baburao);

        System.out.println("\n--  - - -   ---Checkout     -------");
        system.checkout("B01", "ISBN-TWOSTATES-001", "P001");
        system.checkout("B01", "ISBN-GOOSEBUMPS-001", "P002");
        boolean thirdTry = system.checkout("B01", "ISBN-TWOSTATES-001", "P003");
        System.out.println("Third checkout at B01 success?  : " + thirdTry);

        System.out.println("\n-- - - --- - Reservations   - -     ---");
        system.reserveBook("B01", "ISBN-GOOSEBUMPS-001", "P003");
        system.returnBook("B01", "ISBN-GOOSEBUMPS-001", "P002");

        System.out.println("\n------- -  - Transfer copies   -- -   ---");
        boolean transferred = system.transferCopies("B02", "B01", "ISBN-GOOSEBUMPS-001", 1);
        System.out.println("Transferred? " + transferred);

      system.checkout("B01", "ISBN-GOOSEBUMPS-001", "P002");
        system.returnBook("B01", "ISBN-GOOSEBUMPS-001", "P002");

        system.checkout("B02", "ISBN-HIST-001", "P003");
        system.returnBook("B02", "ISBN-HIST-001", "P003");

        System.out.println("\n--  --- - Final inventories    ---");
        system.printAllInventories();
    }
}