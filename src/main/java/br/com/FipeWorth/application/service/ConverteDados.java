package br.com.FipeWorth.application.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try{
            return objectMapper.readValue(json,classe);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Erro ao converter JSON para objeto: " + e.getMessage());
        }
    }

    @Override
    public <T> List<T> obterLista(String json, Class<T> classe) {
        CollectionType collectionType = objectMapper.getTypeFactory()
                .constructCollectionType(List.class, classe);
        try {
            return objectMapper.readValue(json,collectionType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erro ao converter JSON para lista: " + e.getMessage());
        }
    }
}
