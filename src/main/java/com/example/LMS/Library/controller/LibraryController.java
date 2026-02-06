//package com.example.LMS.Library.controller;
//
//import com.example.LMS.Library.model.*;
//import com.example.LMS.Library.repository.*;
//import com.example.LMS.Library.service.LibraryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "*") // Frontend connect aaga idhu mukkiyam
//public class LibraryController {
//
//    // --- Indha lines dhaan neenga miss panninga ---
//    @Autowired private LibraryService service;
//    @Autowired private BookRepository bookRepo;
//    @Autowired private TransactionRepository transRepo;
//    @Autowired private UserRepository userRepo;
//    // ----------------------------------------------
//
//    // --- Auth ---
//    @PostMapping("/login")
//    public User login(@RequestBody User u) {
//        return service.login(u.getEmail(), u.getPassword());
//    }
//
//    @PostMapping("/register")
//    public User register(@RequestBody User u) {
//        u.setRole("USER"); // Default ah USER nu set panrom
//        return userRepo.save(u);
//    }
//
//    // --- Books ---
//    @GetMapping("/books")
//    public List<Book> getBooks() { return bookRepo.findAll(); }
//
//    @PostMapping("/books")
//    public com.example.library.model.Book addBook(@RequestBody com.example.library.model.Book b) { return service.addBook(b); }
//
//    // --- Issue/Return ---
//    @PostMapping("/issue")
//    public String issue(@RequestParam Long bookId, @RequestParam Long userId) {
//        return service.issueBook(bookId, userId);
//    }
//
//    @PostMapping("/return")
//    public String returnBook(@RequestParam Long transId) {
//        return service.returnBook(transId);
//    }
//
//    // --- History ---
//    @GetMapping("/history/{userId}")
//    public <Transaction> List<Transaction> getHistory(@PathVariable Long userId) {
//        return (List<Transaction>) transRepo.findByUserId(userId);
//    }
//
//    @GetMapping("/transactions") // Admin ku matum
//    public List<Transaction> getAllTrans() { return transRepo.findAll(); }
//}

package com.example.LMS.Library.controller;

import com.example.LMS.Library.model.*;
import com.example.LMS.Library.repository.*;
import com.example.LMS.Library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class LibraryController {

    // --- நீங்க மிஸ் பண்ண முக்கியமான வரிகள் (Missing Lines) ---
    @Autowired private LibraryService service;
    @Autowired private BookRepository bookRepo;
    @Autowired private TransactionRepository transRepo;
    @Autowired private UserRepository userRepo;
    // -------------------------------------------------------

    // --- Auth ---
    @PostMapping("/login")
    public User login(@RequestBody User u) {
        return service.login(u.getEmail(), u.getPassword());
    }

    @PostMapping("/register")
    public User register(@RequestBody User u) {
        u.setRole("USER");
        return userRepo.save(u);
    }

    // --- Books ---
    @GetMapping("/books")
    public List<Book> getBooks() { return bookRepo.findAll(); }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book b) { return service.addBook(b); }

    // --- Issue/Return ---
    @PostMapping("/issue")
    public String issue(@RequestParam Long bookId, @RequestParam Long userId) {
        return service.issueBook(bookId, userId);
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam Long transId) {
        return service.returnBook(transId);
    }

    // --- History ---
    @GetMapping("/history/{userId}")
    public List<Transaction> getHistory(@PathVariable Long userId) {
        return transRepo.findByUserId(userId);
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTrans() { return transRepo.findAll(); }
}