package com.example.workshopmongo.services;

import java.util.List;
import java.util.Optional;

///import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.workshopmongo.domain.User;
import com.example.workshopmongo.dto.UserDTO;
import com.example.workshopmongo.repository.UserRepository;
import com.example.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
   
	public List<User> findAll() {
		return repo.findAll() ;
		
	}
	
	public User findById(String id) {
		Optional<User> user = repo.findById(id);
					
		if (user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado.");
			
		}
		///return user.get();
		return user.orElseThrow(() -> new ObjectNotFoundException(id)) ;
		
	}
	
	public User insert(User obj ) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		Optional<User> newObj = repo.findById(obj.getId()) ;
		updateData(newObj, obj);
		
		return repo.save(newObj.get());
		
	}
	
	
	private void updateData(Optional<User> newObj, User obj) {
	
		newObj.get().setName(obj.getName());
		newObj.get().setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		 return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
