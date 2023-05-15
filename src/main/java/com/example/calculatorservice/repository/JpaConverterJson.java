package com.example.calculatorservice.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

import java.util.List;

@Converter(autoApply = true)
public class JpaConverterJson implements AttributeConverter<List<Installment>, String> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final TypeReference<List<Installment>> TYPE_REFERENCE = new TypeReference<List<Installment>>() {
    };

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(List<Installment> installments) {
        return OBJECT_MAPPER.writeValueAsString(installments);
    }

    @Override
    @SneakyThrows
    public List<Installment> convertToEntityAttribute(String json) {
        return OBJECT_MAPPER.readValue(json, TYPE_REFERENCE);
    }
}
