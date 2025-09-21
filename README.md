<img width="2036" height="1076" alt="image" src="https://github.com/user-attachments/assets/0511fcd0-5ea4-454a-a1bb-9d5d8d7d9a0b" />** Library Management System **

* Overview
The Library Management System (LMS) is a Java-based application designed to manage books, patrons, and lending operations across multiple library branches. It includes advanced features such as book reservations, notifications, book transfers between branches, and a recommendation system.
The project demonstrates proper application of OOP principles, SOLID design principles, and use of design patterns (Observer, Builder, Factory, Strategy). It also leverages Java collections like Lists, Queues and Maps.

* Class Diagram
  
  ![Class Diagram](class-diagram.png)


* Features 

1. Book Management
   
Add, remove, and update books in the inventory.
Search books by title, author, or ISBN.
Track availability and borrowed status.

2. Patron Management

Add and update patron details.
Maintain patron borrowing history.

3. Lending Process

Book checkout and return.
Tracks borrowed and available books.

4. Multi-Branch Support

Multiple library branches.
Transfer of books between branches.

5. Reservation System

Patrons can reserve books that are checked out.
Notification system alerts patrons when reserved books are available (Observer pattern).

6. Recommendation System

Provides book recommendations based on patron borrowing history.
Uses only History Based Recommendation strategy as of now. Can be updated further to support other recommendation strategies as well.


* Technical Design

1) OOP Concepts Used

Encapsulation, Inheritance, Polymorphism, Abstraction.

2) SOLID Principles

3) Design Patterns Used

   Builder Pattern - For creating objects of Book entity.
   Observer Pattern — For notifications when reserved books become available. (Patron Observer)
   Strategy Pattern — For different book recommendation strategies(History Based)
   Factory Pattern — Library Factory

* Collections Used
  List, Queue, Deque, Maps 

* Running the Application

  1)Clone the repository:
    git clone https://github.com/harshloshali/library-management-system.git
    cd library-management-system
  2)Compile and Run
    
