package com.example.workshopmongo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	  ///https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#mongodb.repositories.queries
	  ////ex. consulta embutidas no spring x mongoDB STEREOTYPE - query methods
	  List<Post> findByTitleContainingIgnoreCase(String text) ;
	  
	  ////https://docs.mongodb.com/manual/reference/operator/query/regex/
	  ////ex. consulta query embutidas no mongoDB - @query
	  @Query("{ 'title': {$regex: ?0, $options: 'i'} }")
	  List<Post> searchTitle(String text);
	  
	  //// multiplas consultas and e or
	  ///https://docs.mongodb.com/manual/reference/operator/query/and/
	  ///https://docs.mongodb.com/manual/reference/operator/query/gte/
	  @Query("{ $and: [ {date: {$gte: ?1} }, {date: {$lte: ?2} }, { $or: [ { 'title': {$regex: ?0, $options: 'i'} }, { 'body': {$regex: ?0, $options: 'i'} }, { 'comments.text': {$regex: ?0, $options: 'i'} } ] } ] }")
	  List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
