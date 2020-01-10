package com.richardson.dna.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder(builderClassName = "CadeiasDnaBuilder", toBuilder = true)
@Data
@Document(collection = "cadeiasDna")
@JsonDeserialize(builder = CadeiasDna.CadeiasDnaBuilder.class)
@ToString
public class CadeiasDna {
    
    @Id
    private String id;
    private String[] dna;
    
    @JsonPOJOBuilder(withPrefix = "")
    public static class CadeiasDnaBuilder {
    
    }
}

