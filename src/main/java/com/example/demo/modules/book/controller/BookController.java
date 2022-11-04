package com.example.demo.modules.book.controller;

import com.example.demo.common.Paging;
import com.example.demo.common.UID;
import com.example.demo.modules.book.biz.BookRepository;
import com.example.demo.modules.book.model.BookEntity;
import com.example.demo.modules.book.model.BookReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import static com.example.demo.common.Constants.STATUS_ACTIVE;
import static com.example.demo.common.Constants.STATUS_INACTIVE;

@RestController
@RequestMapping(value="/books")
public class BookController {
    @Autowired
    BookRepository bookRepo;

    @GetMapping
    public Page<BookEntity> getAllBooks(@RequestParam(name="page", required=false) Integer pageNum,
                            @RequestParam(name="size", required=false) Integer pageSize, // limit
                            @RequestParam(name="sortBy", required=false) String sortBy,
                            @RequestParam(name="orderBy", required=false) String orderBy) {
        Paging paging = new Paging(pageNum, pageSize, sortBy, orderBy);
        Page<BookEntity> response = bookRepo.findAllByStatus(STATUS_ACTIVE, paging.getPaging());
        return response;
    }

    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable(value="id") String uid) {
        Long id = UID.DecomposeUID(uid).getLocalId();
        return bookRepo.findOneByIdAndStatus(id, STATUS_ACTIVE);
    }

    @PostMapping
    public String createBook(@RequestBody BookReq req) {
        BookEntity book = new BookEntity();
        book.setTitle(req.getTitle());
        book.setYear(req.getYear());
        book.setDescription(req.getDescription());
        book.setPrice(req.getPrice());
        bookRepo.save(book);
        return "Create book successful!";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable(value="id") String uid) {
        Long id = UID.DecomposeUID(uid).getLocalId();
        if (bookRepo.findOneByIdAndStatus(id, STATUS_ACTIVE) == null) {
            return "Book already deleted!";
        }
        bookRepo.deleteBook(id, STATUS_INACTIVE);
        return "Delete book successful!";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable(value="id") String uid, @RequestBody BookReq req) {
        Long id = UID.DecomposeUID(uid).getLocalId();
        BookEntity data = bookRepo.findOneByIdAndStatus(id, STATUS_ACTIVE);
        if (data == null) {
            return "Book already deleted!";
        }

        if (req.getTitle() != null) { data.setTitle(req.getTitle()); }
        if (req.getYear() != null) { data.setYear(req.getYear()); }
        if (req.getDescription() != null) { data.setDescription(req.getDescription()); }
        if (req.getPrice() != null) { data.setPrice(req.getPrice()); }

        bookRepo.save(data);
        return "Book updated successful!";
    }
}

