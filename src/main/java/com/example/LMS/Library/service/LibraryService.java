//package com.example.LMS.Library.service;
//
//import com.example.LMS.Library.model.*;
//import com.example.LMS.Library.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class LibraryService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Autowired
//    private BookRepository bookRepo;
//
//    @Autowired
//    private TransactionRepository transRepo;
//
//    // --- Authentication ---
//    public User login(String email, String password) {
//        User u = userRepo.findByEmail(email);
//        if (u != null && u.getPassword().equals(password)) {
//            return u;
//        }
//        return null; // Login failed
//    }
//
//    // --- Admin Operations ---
//    public Book addBook(Book book) {
//        book.setStatus("AVAILABLE"); // Default status
//        return bookRepo.save(book);
//    }
//
//    // --- Transaction Operations ---
//
//    // Issue a book to a user
//    public String issueBook(Long bookId, Long userId) {
//        Optional<Book> bookOpt = bookRepo.findById(bookId);
//        Optional<User> userOpt = userRepo.findById(userId);
//
//        if (bookOpt.isPresent() && userOpt.isPresent()) {
//            Book book = bookOpt.get();
//            if ("AVAILABLE".equals(book.getStatus())) {
//                Transaction trans = new Transaction();
//                trans.setBook(book);
//                trans.setUser(userOpt.get());
//                trans.setIssueDate(LocalDate.now());
//                trans.setFine(0.0);
//
//                transRepo.save(trans);
//
//                // Update book status
//                book.setStatus("ISSUED");
//                bookRepo.save(book);
//
//                return "Book Issued Successfully";
//            } else {
//                return "Book is already issued.";
//            }
//        }
//        return "Book or User not found.";
//    }
//
//    // Return a book and calculate fine
//    public String returnBook(Long transactionId) {
//        Optional<Transaction> transOpt = transRepo.findById(transactionId);
//
//        if (transOpt.isPresent()) {
//            Transaction trans = transOpt.get();
//
//            if (trans.getReturnDate() == null) {
//                trans.setReturnDate(LocalDate.now());
//
//                // Calculate Fine: Rs. 5 per day after 7 days
//                long daysKept = ChronoUnit.DAYS.between(trans.getIssueDate(), LocalDate.now());
//                if (daysKept > 7) {
//                    double fine = (daysKept - 7) * 5.0;
//                    trans.setFine(fine);
//                } else {
//                    trans.setFine(0.0);
//                }
//
//                // Mark Book as Available again
//                Book book = trans.getBook();
//                book.setStatus("AVAILABLE");
//                bookRepo.save(book);
//
//                transRepo.save(trans);
//                return "Book Returned. Fine Amount: Rs. " + trans.getFine();
//            } else {
//                return "Book was already returned.";
//            }
//        }
//        return "Transaction not found.";
//    }
//}
//
//package com.example.LMS.Library.service;
//
//import com.example.LMS.Library.model.*;
//import com.example.LMS.Library.repository.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;
//import java.util.List;
//
//@Service
//public class LibraryService {
//
//    // --- Neenga miss panna lines idhu dhaan (Database Connections) ---
//    @Autowired private UserRepository userRepo;
//    @Autowired private BookRepository bookRepo;
//    @Autowired private TransactionRepository transRepo;
//    // -----------------------------------------------------------------
//
//    // 1. Login Logic
//    public User login(String email, String password) {
//        User u = userRepo.findByEmail(email);
//        if(u != null && u.getPassword().equals(password)) {
//            return u;
//        }
//        return null;
//    }
//
//    // 2. Add Book Logic
//    public Book addBook(Book book) {
//        book.setStatus("AVAILABLE");
//        return bookRepo.save(book);
//    }
//
//    // 3. Issue Book Logic
//    public String issueBook(Long bookId, Long userId) {
//        // Book iruka nu check panrom
//        Book book = bookRepo.findById(bookId).orElse(null);
//        // User iruka nu check panrom
//        User user = userRepo.findById(userId).orElse(null);
//
//        if(book != null && user != null && "AVAILABLE".equals(book.getStatus())) {
//            Transaction t = new Transaction();
//            t.setBook(book);
//            t.setUser(user);
//            t.setIssueDate(LocalDate.now());
//            transRepo.save(t);
//
//            book.setStatus("ISSUED");
//            bookRepo.save(book);
//            return "Book Issued Successfully!";
//        }
//        return "Book not available or User not found.";
//    }
//
//    // 4. Return Book Logic
//    public String returnBook(Long transId) {
//        Transaction t = transRepo.findById(transId).orElse(null);
//
//        if(t != null && t.getReturnDate() == null) {
//            t.setReturnDate(LocalDate.now());
//
//            // Fine Calculation (7 days ku mela pona, 5 Rs fine)
//            long days = ChronoUnit.DAYS.between(t.getIssueDate(), LocalDate.now());
//            if(days > 7) {
//                t.setFine((days - 7) * 5.0);
//            } else {
//                t.setFine(0.0);
//            }
//
//            // Book ah thirumba 'AVAILABLE' nu mathurom
//            Book b = t.getBook();
//            b.setStatus("AVAILABLE");
//            bookRepo.save(b);
//
//            transRepo.save(t);
//            return "Returned Successfully. Fine Amount: Rs. " + t.getFine();
//        }
//        return "Invalid Transaction ID";
//    }
//}
package com.example.LMS.Library.service;

import com.example.LMS.Library.model.*;
import com.example.LMS.Library.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LibraryService {

    // --- நீங்க மிஸ் பண்ண வரிகள் (Missing Connections) ---
    @Autowired private UserRepository userRepo;
    @Autowired private BookRepository bookRepo;
    @Autowired private TransactionRepository transRepo;
    // ----------------------------------------------------

    // 1. Login Logic
    public User login(String email, String password) {
        User u = userRepo.findByEmail(email);
        if(u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }

    // 2. Add Book Logic
    public Book addBook(Book book) {
        book.setStatus("AVAILABLE");
        return bookRepo.save(book);
    }

    // 3. Issue Book Logic
    public String issueBook(Long bookId, Long userId) {
        Book book = bookRepo.findById(bookId).orElse(null);
        User user = userRepo.findById(userId).orElse(null);

        if(book != null && user != null && "AVAILABLE".equals(book.getStatus())) {
            Transaction t = new Transaction();
            t.setBook(book);
            t.setUser(user);
            t.setIssueDate(LocalDate.now());
            transRepo.save(t);

            book.setStatus("ISSUED");
            bookRepo.save(book);
            return "Book Issued Successfully!";
        }
        return "Book not available or User not found.";
    }

    // 4. Return Book Logic
    public String returnBook(Long transId) {
        Transaction t = transRepo.findById(transId).orElse(null);

        if(t != null && t.getReturnDate() == null) {
            t.setReturnDate(LocalDate.now());

            // Fine Calculation
            long days = ChronoUnit.DAYS.between(t.getIssueDate(), LocalDate.now());
            if(days > 7) {
                t.setFine((days - 7) * 5.0);
            } else {
                t.setFine(0.0);
            }

            Book b = t.getBook();
            b.setStatus("AVAILABLE");
            bookRepo.save(b);

            transRepo.save(t);
            return "Returned Successfully. Fine Amount: Rs. " + t.getFine();
        }
        return "Invalid Transaction ID";
    }
}

