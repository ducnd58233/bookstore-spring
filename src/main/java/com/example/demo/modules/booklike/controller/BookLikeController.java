package com.example.demo.modules.booklike.controller;

import com.example.demo.common.UID;
import com.example.demo.modules.booklike.biz.BookLikeRepository;
import com.example.demo.modules.booklike.model.BookLikeEntity;
import com.example.demo.modules.booklike.model.BookLikeId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/books")
public class BookLikeController {
    @Autowired
    BookLikeRepository blRepo;

    @GetMapping("/{bid}/liked-users")
    public List<BookLikeEntity> getLikedUsers(@PathVariable(value="bid") String bid) {
        Integer bRid = UID.DecomposeUID(bid).getLocalId();
        return blRepo.findAllByBookLikeIdBookRid(bRid);
    }

    @PostMapping("/{bid}/{uid}/like")
    public String likeBook(@PathVariable(value="bid") String bid, @PathVariable(value="uid") String uid) {
        Integer bRid = UID.DecomposeUID(bid).getLocalId();
        Integer uRid = UID.DecomposeUID(uid).getLocalId();
        if (blRepo.findByBookLikeIdBookRidAndBookLikeIdUserRid(bRid, uRid) != null) return "User already likes this book";
        BookLikeId blid = new BookLikeId(bRid, uRid);
        BookLikeEntity bl = new BookLikeEntity();
        bl.setBookLikeId(blid);
        blRepo.save(bl);
        return "User like successful!";
    }

    @DeleteMapping("/{bid}/{uid}/like")
    public String unlikeBook(@PathVariable(value="bid") String bid, @PathVariable(value="uid") String uid) {
        Integer bRid = UID.DecomposeUID(bid).getLocalId();
        Integer uRid = UID.DecomposeUID(uid).getLocalId();
        BookLikeEntity bl = blRepo.findByBookLikeIdBookRidAndBookLikeIdUserRid(bRid, uRid);
        if (bl == null) return "User does not likes this book";
        blRepo.delete(bl);
        return "User unlike successful!";
    }
}
