package com.example.demo.modules.book.biz;

import com.example.demo.modules.book.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    // Get all books
    Page<BookEntity> findAllByStatus(int status, Pageable pageable);
    List<BookEntity> findByTitleContaining(String title);
    List<BookEntity> findByYear(int year);

    // Get book
    BookEntity findOneByIdAndStatus(Long id, int status);

    // Update book
    @Transactional
    @Modifying
    @Query(nativeQuery=true, value="UPDATE books SET title=?3 " +
            "AND year=?4 " +
            "AND description=?5" +
            "AND price=?6" +
            "WHERE id=?1 status=?2")
    BookEntity updateBook(Long id, int status, String title, int year, String description, double price);

    // Soft delete book
    @Transactional
    @Modifying
    @Query(nativeQuery=true, value="UPDATE books SET status=?3 WHERE id=?1 AND status=?2")
    BookEntity deleteBook(Long id, int fromStatus, int toStatus);
}
