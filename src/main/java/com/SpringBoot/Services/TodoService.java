package com.SpringBoot.com.Services;

import com.SpringBoot.Todo;
import com.SpringBoot.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;


    public List<Todo> findByUser(String id) {
        return todoRepository.findByUserId(id);
    }
    public  Todo GetTodo(String id){
        return todoRepository.findById(id).get();
    }
    public Todo SaveTodo(Todo todo){
        return todoRepository.insert(todo);
    }
    public Todo UpdateTodo(Todo todo){
        return todoRepository.save(todo);
    }
    public void deleteTodo(String id){
        todoRepository.deleteById(id);
    }
}
