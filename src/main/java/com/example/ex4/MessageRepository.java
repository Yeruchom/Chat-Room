package com.example.ex4;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//declaration off the functions needed for handling the db
public interface MessageRepository extends JpaRepository<Message, Long> {
    @Override
    List<Message> findAllById(Iterable<Long> iterable);
    List<Message> findAllByName(String name);
    List<Message> findAllByTextContains(String text);
    List<Message> findFirst5ByOrderByIdDesc();
    Message findFirstByOrderByIdDesc();

}
