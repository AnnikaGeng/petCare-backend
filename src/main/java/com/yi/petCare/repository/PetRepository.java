package com.yi.petCare.repository;

import com.yi.petCare.pet.Pet;
import com.yi.petCare.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    List<Pet> findByCustomer_id(Long ownerId);
    List<Pet> findByCustomer(Customer customer);
    void deleteById(Long id);
}
