package org.buelna.jpaonetoone.dtos.club;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RequestClubDto implements Serializable {
    private Long id;
    private String name;
    private Long coachId;
    private List<Long> competitionIds;
}