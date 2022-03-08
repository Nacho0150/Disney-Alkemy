package com.disney.alkemy.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterFilterDTO {
    String name;
    Integer age;
    List<Long> filmsorseries;
}