package com.vitgon.schedule.serialize;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.vitgon.schedule.dto.Days;

@Component
public class StringToDaysEnumDeserializer extends StdDeserializer<Days> {
	
	public StringToDaysEnumDeserializer() {
		this(null);
	}

	protected StringToDaysEnumDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Days deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return Days.valueOf(p.getText().toUpperCase());
	}
}
