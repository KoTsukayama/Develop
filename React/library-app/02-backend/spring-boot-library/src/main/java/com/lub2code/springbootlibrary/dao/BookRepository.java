package com.lub2code.springbootlibrary.dao;

import com.lub2code.springbootlibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
}
