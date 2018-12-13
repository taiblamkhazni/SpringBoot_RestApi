package com.SpringBoot;
import com.SpringBoot.com.Services.TodoService;
import com.SpringBoot.security.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RestApiController  extends BaseController{

    @Autowired
    private TodoService todoService;
    @Autowired
    private  TodoRepository todoRepository;

    @GetMapping(value = {"","/"})
    public ResponseEntity<?> AllTodos() {
        List<Todo> result= todoService.findByUser(getCurrentUser().getId());
        if (result==null){
            return new ResponseEntity<>(new ResponseMessage("Fail -> Sorry don't have any Todo!"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> GetTodoById(@PathVariable String id){
        if (todoRepository.existsByUserId(getCurrentUser().getId())){
            Todo result =todoService.GetTodo(id);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("Fail -> Sorry don't have any Todo!"),
                HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/create")
    public ResponseEntity<Todo> AddTodo(@Valid @RequestBody Todo todo){
        todo.setUserId(getCurrentUser().getId());
        Todo result=todoService.SaveTodo(todo);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }
    @PutMapping("/Update")
    public ResponseEntity<?> UpdateTodo(@RequestBody Todo todo){
        if (todo.getUserId().equals(getCurrentUser().getId())) {
            Todo result = todoService.UpdateTodo(todo);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseMessage("Fail -> sorry don't Update this  Todo!"),
                HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteTodo(@PathVariable String id){
        if (todoRepository.existsByUserId(getCurrentUser().getId())){
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ResponseMessage("Fail -> Sorry don't Delete this Todo!"),
                HttpStatus.BAD_REQUEST);
    }

}
