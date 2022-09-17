package com.listas.handler;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FieldsExceptionOutput {

	private String name;
	private String message;

}
