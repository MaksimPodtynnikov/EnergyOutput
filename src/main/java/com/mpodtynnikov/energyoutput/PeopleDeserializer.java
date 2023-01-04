package com.mpodtynnikov.energyoutput;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.NumericNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;

public class PeopleDeserializer extends JsonDeserializer<People> {
    @Override
    public People deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        TreeNode node = jsonParser.readValueAsTree();
        Sex sex = Sex.valueOf(((TextNode)node.get("sex")).asText());
        TextNode title = (TextNode)node.get("title");
        NumericNode count = (NumericNode)node.get("count");
        Age age = Age.valueOf(((TextNode)node.get("age")).asText());
        IMB imb = IMB.valueOf(((TextNode)node.get("imb")).asText());
        return new People(sex,age,imb,title.textValue(),count.intValue());
    }
}
