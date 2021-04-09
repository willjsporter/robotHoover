package com.willjsporter.controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.willjsporter.model.Pair;

import java.io.IOException;

public class PairDeserializer extends StdDeserializer<Pair> {

    public PairDeserializer() {
        this(null);
    }

    public PairDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Pair deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);

        return getPair(node);
    }

    private Pair getPair(JsonNode arrNode) {
        if (arrNode.isArray() && arrNode.size() == 2) {
            return Pair.of(arrNode.get(0).asInt(), arrNode.get(1).asInt());
        } else return null;
    }
}