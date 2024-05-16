package org.buelna.jpaonetoone.dtos.coach;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCoachDto implements Serializable {

    private String name;

    private String lastName;

    private String nationality;

    private Integer age;
}
