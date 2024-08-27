package com.example.BookstoreAPI.assembler;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import com.example.BookstoreAPI.controller.BookController;
import com.example.BookstoreAPI.model.dto.BookDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class BookModelAssembler extends RepresentationModelAssemblerSupport<BookDTO, EntityModel<BookDTO>> {
	
	 public BookModelAssembler() {
		 super(BookController.class, (Class<EntityModel<BookDTO>>) (Class<?>) EntityModel.class);
	    }
	
	 @Override
	    public EntityModel<BookDTO> toModel(BookDTO bookDTO) {
	        // Create the EntityModel and add links to it
	        EntityModel<BookDTO> bookModel = EntityModel.of(bookDTO);

	        // Add self link and other related links as needed
	        bookModel.add(linkTo(methodOn(BookController.class).getBookById(bookDTO.getId())).withSelfRel());

	        // Add other links if necessary, e.g., update, delete
	        bookModel.add(linkTo(methodOn(BookController.class).updateBook(bookDTO.getId(), null)).withRel("update"));
	        bookModel.add(linkTo(methodOn(BookController.class).deleteBook(bookDTO.getId())).withRel("delete"));

	        return bookModel;
	    }
}
