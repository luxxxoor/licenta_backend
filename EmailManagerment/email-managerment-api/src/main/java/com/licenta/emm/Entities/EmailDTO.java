package com.licenta.emm.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    private Integer id;
    private String email;
    private Boolean confirmed;

    public EmailDTO(final Integer id, final String email){
        this.id = id;
        this.email = email;
    }
}
