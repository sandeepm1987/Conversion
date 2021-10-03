package com.momentum.conversion.Model;

import com.momentum.conversion.helper.Constants;
import lombok.Data;

@Data
public class CommonModel extends ConvertModel {
    private String from;
    private String to;

    public CommonModel(Constants.Service type, String from, String to, String input) {
        super(type, input);
        this.from = from.toUpperCase();
        this.to = to.toUpperCase();
    }
}
