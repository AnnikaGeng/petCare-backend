package com.yi.petCare.pet;

import com.yi.petCare.user.Customer;
import com.yi.petCare.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        return convertPetToPetDTO(petService.save(convertPetDTOToPet(petDTO)));
    }
    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        return convertPetToPetDTO(pet);
    }

    @PatchMapping ("/{petId}")
    public PetDTO updatePet(@PathVariable long petId, @RequestBody PetDTO petDTO) {
        Pet pet = petService.getPetById(petId);
        BeanUtils.copyProperties(convertPetDTOToPet(petDTO), pet);
        return convertPetToPetDTO(petService.save(pet));
    }

    @DeleteMapping("/{petId}")
    public void deletePet(@PathVariable long petId) {
        petService.deletePetById(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<Pet> pets = petService.findAllPets();
        List<PetDTO> petDTOS = pets.stream().map(this::convertPetToPetDTO).collect(Collectors.toList());
        return petDTOS;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<Pet> pets = petService.findByCustomerId(ownerId);
        List<PetDTO> petDTOS = new ArrayList<>();
        pets.forEach(pet -> {
            petDTOS.add(convertPetToPetDTO(pet));
        });
        return petDTOS;
    }

    private PetDTO convertPetToPetDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO);
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setType(pet.getType());
        return petDTO;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);
        Customer customer = customerService.getCustomerById(petDTO.getOwnerId());
        pet.setCustomer(customer);
        pet.setType(petDTO.getType());
        return pet;
    }
}
