package com.yi.petCare.repository;

import com.yi.petCare.schedule.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPets_Id(Long id);
    List<Schedule> findByEmployees_Id(Long id);

    List<Schedule> findAllByPets_id(Long petId);
}
