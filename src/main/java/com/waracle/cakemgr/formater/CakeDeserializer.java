package com.waracle.cakemgr.formater;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.waracle.cakemgr.vo.CakeVO;

import java.io.IOException;

public class CakeDeserializer extends JsonDeserializer<CakeVO> {

    @SuppressWarnings("DuplicateThrows")
    @Override
    public CakeVO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        ObjectCodec objectCodec = jsonParser.getCodec();
        JsonNode node = objectCodec.readTree(jsonParser);
        return CakeVO.builder()
                .setTitle(node.get("title").asText())
                .setDesc(node.get("desc").asText())
                .setImage(node.get("image").asText())
                .build();
    }

}
