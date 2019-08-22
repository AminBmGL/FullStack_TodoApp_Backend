package com.insat.restfulapi.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.insat.restfulapi.domain.Todo;
import com.insat.restfulapi.services.TodoService;

@RestController
@CrossOrigin
public class TodoController {

	@Autowired
	private TodoService todoService;

	@GetMapping("/api/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username) {
		return null;

	}
	
	@GetMapping("/api/{username}/todos/{id}")
	public List<Todo> getTodo(@PathVariable long id) {
		return null;

	}
	
	@PostMapping("/api/{username}/todos")
	public ResponseEntity<Void> addTodo(@PathVariable String username,
			 				  @RequestBody Todo todo) {
Todo addedTodo =todoService.save(todo);

URI uri= ServletUriComponentsBuilder.fromCurrentRequest()
.path("/{id}").buildAndExpand(addedTodo.getId()).toUri();

return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/api/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(@PathVariable String username,
								 @PathVariable long id,
								 @RequestBody Todo todo) {
Todo updatedTodo=todoService.save(todo);
return new ResponseEntity<Todo>(updatedTodo, HttpStatus.OK);
	}

	@DeleteMapping("/api/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable long id) {
		Todo todo = todoService.deleteById(id);
		if(todo!=null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
