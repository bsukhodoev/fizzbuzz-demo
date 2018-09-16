package org.bsukhodoev.fuzbuzdemo.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FizzBuzzRequest {
    @NotEmpty
    @ApiModelProperty(value = "Positive numbers to convert", required = true, example = "[1, 2, 3, 4, 5, 14, 15, 16]")
    private List<@NotNull @Positive Integer> numbers;
}
