package com.personal.cl.annotation;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class SecuritySerializeImpl extends JsonSerializer<String> {

    @Override
    public void serialize(String string, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (!StringUtils.hasText(string) || string.length() < 4) {
            jsonGenerator.writeString(string);
        }
        var chars = string.toCharArray();
        for (int i = 3; i < chars.length - 4; i++) {
            chars[i] = '*';
        }
        jsonGenerator.writeString(new String(chars));
    }

}
