package com.disney.alkemy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmorSerieFilterDTO {
    private String title;
    private Long genderId;
    private String creationdate;
    private String order;
    
    public boolean isASC() {return order.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() {return order.compareToIgnoreCase("DESC") == 0;}
}