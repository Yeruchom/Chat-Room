package com.example.ex4;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Override
    List<Message> findAllById(Iterable<Long> iterable);
}
