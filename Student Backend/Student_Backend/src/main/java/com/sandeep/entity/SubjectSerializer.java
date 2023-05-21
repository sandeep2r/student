package com.sandeep.entity;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class SubjectSerializer extends JsonSerializer<Subject> {

    public void serialize(Subject subject, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", subject.getId());
        jsonGenerator.writeStringField("name", subject.getName());
        jsonGenerator.writeStringField("code", subject.getCode());
        jsonGenerator.writeStringField("theoryOrPractical", subject.getTheoryOrPractical());
        jsonGenerator.writeNumberField("studentId", subject.getStudent().getId()); // Include studentId
        jsonGenerator.writeEndObject();
    }
}
