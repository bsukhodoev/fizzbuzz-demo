package org.bsukhodoev.fuzbuzdemo.rest;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FizzBuzzRestController {
    @PostMapping("/fizzbuzz")
    @ResponseBody
    @ApiOperation(value = "Replaces numbers divisible by 3 to \"fizz\", divisible by 5 to \"buzz\", " +
            "divisible by 3 and 5 to \"fizz buzz\" words.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "All numbers processed successfully"),
            @ApiResponse(code = 400, message = "If wrong request provided")
    })
    public FizzBuzzResponse fuzzbuzz(@Valid @RequestBody FizzBuzzRequest request) {
        List<String> result = request.getNumbers().stream()
                .map(n -> {
                    if (n % 15 == 0) {
                        return "fizz buzz";
                    }
                    else if (n % 3 == 0) {
                        return "fizz";
                    }
                    else if (n % 5 == 0) {
                        return "buzz";
                    }

                    return String.valueOf(n);
                })
                .collect(Collectors.toList());
        return new FizzBuzzResponse(result);
    }
}
