package org.buelna.jpaonetoone.services;

import org.buelna.jpaonetoone.dtos.club.RequestClubDto;
import org.buelna.jpaonetoone.dtos.club.ResponseClubDto;

import java.util.List;

public interface ClubService {

    List<ResponseClubDto> getAllClubs();

    ResponseClubDto getClubById(Long id);

    ResponseClubDto saveClub(RequestClubDto club);

    ResponseClubDto updateClub(RequestClubDto club);

    void deleteClub(Long id);
}
