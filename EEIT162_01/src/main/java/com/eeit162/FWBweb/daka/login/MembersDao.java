package com.eeit162.FWBweb.daka.login;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MembersDao extends JpaRepository<Members, Integer>{
  public Members findByEmailAndPassword(String email,String password);
  public Members findByEmail (String email);

  public	Members findByCode(String code);
  
  
//找配對者，性別符合使用者的性向。並且使用者的性別符合配對者的性向。
  @Query("FROM Members m WHERE m.gender = :m1detailsexori AND (m.memberDetail.sexualOrientation = :gender OR m.memberDetail.sexualOrientation IS NULL)")
  List<Members> findBySexOriAndGender(@Param(value = "m1detailsexori") String SexOri,@Param(value = "gender") String gender);
  
}