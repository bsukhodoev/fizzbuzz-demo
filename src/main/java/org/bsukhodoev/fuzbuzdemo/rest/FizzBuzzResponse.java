package org.bsukhodoev.fuzbuzdemo.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FizzBuzzResponse {
    @ApiModelProperty(value = "Converted (replaced by words) numbers", required = true,
            example = "[\"1\", \"2\", \"fizz\", \"4\", \"buzz\", \"14\", \"fizz buzz\", \"16\"]")
    public List<String> result;
}
