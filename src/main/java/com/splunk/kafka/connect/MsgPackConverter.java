package com.splunk.kafka.connect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaAndValue;
import org.apache.kafka.connect.storage.Converter;
import org.msgpack.jackson.dataformat.MessagePackFactory;

public class MsgPackConverter implements Converter {

  private final ObjectMapper objectMapper = new ObjectMapper(new MessagePackFactory());



  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {


  }

  @Override
  public byte[] fromConnectData(String topic, Schema schema, Object o) {
    try {
      return objectMapper.writeValueAsBytes(o);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public SchemaAndValue toConnectData(String s, byte[] value) {
    try {
      return new SchemaAndValue(Schema.OPTIONAL_STRING_SCHEMA,
          objectMapper.readValue(value, JsonNode.class));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
