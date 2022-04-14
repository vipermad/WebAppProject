package com.example.com.example.repository;

import com.example.com.example.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    @Query("select t from Task t where t.id = :id")
    Task findByIdOvveride(@Param("id") int id);

    @Modifying
    @Query("UPDATE Task t SET t.text = :newTask WHERE t.id = :id")
    void updateTask(@Param("id") int id, @Param("newTask") String newTask);
}

