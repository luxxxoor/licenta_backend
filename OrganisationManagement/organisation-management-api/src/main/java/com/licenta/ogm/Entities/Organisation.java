package com.licenta.ogm.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organisation {
    private Integer id;
    private String name;
    private Boolean isUserSubscriber;
}
