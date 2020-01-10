package com.richardson.dna.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@JsonPropertyOrder({ "count_mutant_dna", "count_human_dna", "ration" })
public class EstatisticasDna {
    @ApiModelProperty(dataType = "Integer", notes = "Contagem de DNA mutante", position = 1)
    private Integer count_mutant_dna = 0;
    @ApiModelProperty(dataType = "Integer", notes = "Contagem de DNA humano", position = 2)
    private Integer count_human_dna = 0;
    @ApiModelProperty(dataType = "Double", notes = "Proporção mutantes x humanos", position = 3)
    private Double ratio = 0.0D;
}