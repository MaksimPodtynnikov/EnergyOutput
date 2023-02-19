package com.mpodtynnikov.energyoutput.JSONParser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PeoplesSerializer extends JsonSerializer<PeoplesState> {
    @Override
    public void serialize(PeoplesState peoples, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("title", peoples.title);
        jsonGenerator.writeNumberField("count", peoples.count);
        jsonGenerator.writeStringField("place", peoples.place);
        jsonGenerator.writeNumberField("energy", peoples.energy);
        jsonGenerator.writeNumberField("o2", peoples.o2);
        jsonGenerator.writeNumberField("co2", peoples.co2);
        jsonGenerator.writeFieldName("groups");
        jsonGenerator.writeStartArray();
        for (PeopleState people: peoples.peopleStates) {
            jsonGenerator.writeStartObject(people);
            jsonGenerator.writeStringField("title", people.title);
            jsonGenerator.writeNumberField("count", people.count);
            jsonGenerator.writeStringField("sex", people.sex);
            jsonGenerator.writeStringField("birthday", people.age);
            jsonGenerator.writeStringField("imb", people.imb);
            jsonGenerator.writeNumberField("energy", people.energy);
            jsonGenerator.writeNumberField("o2", people.o2);
            jsonGenerator.writeNumberField("co2", people.co2);
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeFieldName("collections");
        jsonGenerator.writeStartArray();
        for (PeoplesState collection: peoples.peoplesStates) {
            this.serialize(collection,jsonGenerator,serializerProvider);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
