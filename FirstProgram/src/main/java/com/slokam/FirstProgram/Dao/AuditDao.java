package com.slokam.FirstProgram.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.slokam.FirstProgram.Pojo.AuditPojo;

@Repository 
public interface AuditDao extends JpaRepository<AuditPojo, Integer> {

}
