package org.buelna.jpaonetoone.dtos.player;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SavePlayerRequest implements Serializable {

    private String name;

    private String lastName;

    private String nationality;

    private Integer age;

    private String position;

    private Long club;
}
