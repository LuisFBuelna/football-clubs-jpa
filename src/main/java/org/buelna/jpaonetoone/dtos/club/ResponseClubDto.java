package org.buelna.jpaonetoone.dtos.club;

import lombok.*;
import org.buelna.jpaonetoone.dtos.footballcompetition.ResponseClubCompetitionDto;
import org.buelna.jpaonetoone.dtos.coach.ResponseCoachDto;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResponseClubDto implements Serializable {

    private String name;
    private ResponseCoachDto coach;
    private List<ResponseClubCompetitionDto> competition;
}
