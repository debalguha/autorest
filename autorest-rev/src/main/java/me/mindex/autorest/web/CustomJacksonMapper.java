package me.mindex.autorest.web;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("serial")
public class CustomJacksonMapper extends ObjectMapper {

	public CustomJacksonMapper(String dateFormat) {
	    super();
	    this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    this.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
	    this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    this.getSerializerProvider().setNullValueSerializer(new NullSerializer());
	    this.setDateFormat(new SimpleDateFormat(dateFormat));
	}
	
	public CustomJacksonMapper() {
	    super();
	    this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    this.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,true);
	    this.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	    this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	    this.getSerializerProvider().setNullValueSerializer(new NullSerializer());
	    this.setDateFormat(new SimpleDateFormat("MM/dd/yyyy"));
	}
	
	@Override
	public <T> T readValue(InputStream src, JavaType valueType) throws IOException, JsonParseException, JsonMappingException {
		if(valueType.getRawClass().equals(Date.class))
			System.out.println("Hello!!");
		return super.readValue(src, valueType);
	}
}

class NullSerializer extends JsonSerializer<Object> {
	public void serialize(Object value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
		jgen.writeString("");
	}
}