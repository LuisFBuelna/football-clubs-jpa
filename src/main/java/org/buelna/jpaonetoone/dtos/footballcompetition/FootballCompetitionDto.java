package org.buelna.jpaonetoone.dtos.footballcompetition;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FootballCompetitionDto implements Serializable {

    private String name;

    private Integer quantityPrice;

    private LocalDate startDate;

    private LocalDate endDate;
}
