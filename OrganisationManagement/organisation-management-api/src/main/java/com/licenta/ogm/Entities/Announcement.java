package com.licenta.ogm.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer id;
    private String title;
    private String imageUrl;
    private String description;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String date;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String organisationName;
}
