package org.buelna.jpaonetoone.dtos.footballcompetition;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClubCompetitionDto implements Serializable {

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
