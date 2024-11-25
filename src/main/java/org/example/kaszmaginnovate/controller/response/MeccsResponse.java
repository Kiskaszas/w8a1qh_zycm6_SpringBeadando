package org.example.kaszmaginnovate.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.kaszmaginnovate.model.Meccs;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeccsResponse {

    private boolean success;
    private String message;
    private List<String> errors;
    private Meccs updatedMeccs;
}
