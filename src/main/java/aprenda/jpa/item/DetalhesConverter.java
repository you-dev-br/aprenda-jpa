package aprenda.jpa.item;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;

import java.io.IOException;

public class DetalhesConverter implements AttributeConverter<Detalhes, byte[]> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] convertToDatabaseColumn(Detalhes attribute) {
        try {
            return objectMapper.writeValueAsBytes(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }qqq

    @Override
    public Detalhes convertToEntityAttribute(byte[] attribute) {
        try {
            return objectMapper.readValue(attribute, Detalhes.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
