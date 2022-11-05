package com.example.demo.modules.booklike.biz;

import com.example.demo.modules.booklike.model.BookLikeEntity;
import com.example.demo.modules.booklike.model.BookLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookLikeRepository extends JpaRepository<BookLikeEntity, BookLikeId> {
    List<BookLikeEntity> findAllByBookLikeIdBookRid(int b_id);
    BookLikeEntity findByBookLikeIdBookRidAndBookLikeIdUserRid(int b_id, int u_id);

    @Query(nativeQuery=true, value="SELECT COUNT(user_id) FROM book_likes WHERE book_id=?1")
    Integer getTotalLikes(int b_id);
}
