package com.example.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	////@GetMapping
	/// metodo abaixo antes da existencia das classe UserService e interface UserRepository
	/// chama o obejto User direto sem implementacao das camadas repository e service
	/*
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		User maria = new User("1","Maria Silva","mariagmail.com") ;
		User joao = new User("1","joao Silva","joaogmail.com") ;
		List<User> list = new ArrayList<>();
		
		list.addAll(Arrays.asList(maria,joao)) ;
		
		////return list;
		return ResponseEntity.ok().body(list);
	}
	*/
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<User>> findAll(){
		
		List<User> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
}
