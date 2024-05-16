package org.buelna.jpaonetoone.dtos.player;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PlayerRequestDto implements Serializable {

    private Long id;
    private Long clubId;
}
