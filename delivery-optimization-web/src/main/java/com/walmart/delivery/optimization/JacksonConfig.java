package com.walmart.delivery.optimization;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author renannp
 * 
 * This class is responsible by configuring various characteristics of the Jackson library
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class JacksonConfig implements ContextResolver<ObjectMapper>
{
    private final ObjectMapper objectMapper;
            
    public JacksonConfig()
    {
        objectMapper = new ObjectMapper() 
        {{
            // this is used usually for debugging, where the json will be "pretty printed" to the output
            this.configure(SerializationFeature.INDENT_OUTPUT, true);
//            this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        }};
    }
    @Override
    public ObjectMapper getContext(Class<?> objectType)
    {
        return objectMapper;
    }
}