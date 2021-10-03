package com.momentum.conversion.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.momentum.conversion.helper.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConvertModel {

    @JsonIgnore
    private Constants.Service type;
    private String input;
    private String result;

    public ConvertModel(Constants.Service type, String input) {
        this.type=type;
        this.input=input;

    }
}
