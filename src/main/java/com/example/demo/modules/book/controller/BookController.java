package com.example.demo.modules.book.controller;

import com.example.demo.common.Paging;
import com.example.demo.modules.book.biz.BookRepository;
import com.example.demo.modules.book.model.BookEntity;
import com.example.demo.modules.book.model.BookReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


import static com.example.demo.common.Constants.STATUS_ACTIVE;

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
    public BookEntity getBookById(@PathVariable(value="id") Long id) {
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
}

