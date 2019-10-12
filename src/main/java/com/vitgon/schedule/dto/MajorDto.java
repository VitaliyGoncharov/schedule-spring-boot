package com.vitgon.schedule.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.vitgon.schedule.view.Views;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorDto {
	
	@JsonView(Views.Public.class)
	private Integer id;
	
	@JsonView(Views.Public.class)
	private String name;
	
	@JsonView(Views.Public.class)
	private String url;
	
	@JsonView(Views.AdminPanel.class)
	private String translation;
}
