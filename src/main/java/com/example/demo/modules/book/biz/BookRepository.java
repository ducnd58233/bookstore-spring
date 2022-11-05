package com.example.demo.modules.book.biz;

import com.example.demo.modules.book.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
    // Get all books
    Page<BookEntity> findAllByStatus(int status, Pageable pageable);

    // Get book
    BookEntity findOneByIdAndStatus(Integer id, int status);

    // Soft delete book
    @Transactional
    @Modifying
    @Query(nativeQuery=true, value="UPDATE books SET status=?2 WHERE id=?1")
    void deleteBook(Integer id, int toStatus);
}
