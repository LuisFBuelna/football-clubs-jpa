package org.buelna.jpaonetoone.dtos.player;

import lombok.*;
import org.buelna.jpaonetoone.dtos.club.ResponseClubDto;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResponseDto implements Serializable {

    private String name;

    private String lastName;

    private String nationality;

    private Integer age;

    private String position;

    private ResponseClubDto club;

    public PlayerResponseDto(String name, String lastName, String nationality, Integer age, String position) {
        this.name = name;
        this.lastName = lastName;
        this.nationality = nationality;
        this.age = age;
        this.position = position;
    }

}
