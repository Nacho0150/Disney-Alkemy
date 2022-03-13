package com.disney.alkemy.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CharacterFilterDTO {
    private String name;
    private Integer age;
    private List<Long> filmsorseries;
}