package com.mpodtynnikov.energyoutput;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PeopleSerializer extends JsonSerializer<PeopleState> {
    @Override
    public void serialize(PeopleState people, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("title", people.title);
        jsonGenerator.writeNumberField("count", people.count);
        jsonGenerator.writeStringField("sex", people.sex);
        jsonGenerator.writeStringField("age", people.age);
        jsonGenerator.writeStringField("imb", people.imb);
        jsonGenerator.writeNumberField("energy", people.energy);
        jsonGenerator.writeNumberField("o2", people.o2);
        jsonGenerator.writeNumberField("co2", people.co2);
        jsonGenerator.writeEndObject();
    }
}
