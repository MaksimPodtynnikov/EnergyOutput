package com.mpodtynnikov.energyoutput.JSONParser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.mpodtynnikov.energyoutput.*;
import com.mpodtynnikov.energyoutput.Model.Dao;

import java.io.IOException;

public class PeoplesDeserializer extends JsonDeserializer<Peoples> {
    Dao generator;
    public PeoplesDeserializer(Dao generator)
    {
        this.generator = generator;
    }
    @Override
    public Peoples deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode node = jsonParser.readValueAsTree();
        return collectionDeserialize((JsonNode) node);
    }
    private Peoples collectionDeserialize(JsonNode node)
    {
        TextNode title = (TextNode)node.get("title");
        TextNode place = (TextNode)node.get("place");
        Peoples peoples = new Peoples(title.textValue(),place.asText(),generator);
        for (JsonNode person: node.get("groups")) {
            peoples.add(new People(Sex.valueOf(person.get("sex").asText()),Age.valueOf(person.get("birthday").asText()),IMB.valueOf(person.get("imb").asText()),
                    person.get("title").asText(), person.get("count").asInt(),generator));
        }
        for (JsonNode collection: node.get("collections")) {
            peoples.addCollection(collectionDeserialize(collection));
        }
        return peoples;
    }
}
