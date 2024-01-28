package com.spring.library.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {
	
	private Integer id;
	
	@NotEmpty
    private String title;
    
	@NotEmpty
    private String author;
    
	@NotEmpty
    private String edition;
}
