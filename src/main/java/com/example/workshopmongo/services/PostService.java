package com.example.workshopmongo.services;

import java.util.List;
import java.util.Optional;

///import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.Post;
import com.example.workshopmongo.repository.PostRepository;
import com.example.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
   
		
	public Post findById(String id) {
		Optional<Post> user = repo.findById(id);
					
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado.");
			
		}
		///return user.get();
		return user.orElseThrow(() -> new ObjectNotFoundException(id)) ;
		
	}
	
	
	public List<Post> findByTitle(String text) {
		 return repo.findByTitleContainingIgnoreCase(text) ;
	}
}
