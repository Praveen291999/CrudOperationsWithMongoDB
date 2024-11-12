package mongoCrudTemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import mongoCrudTemplate.entity.Pet;
import mongoCrudTemplate.exception.CustomException;
import mongoCrudTemplate.repository.PetRepo;

@Service
public class PetService {
	
	@Autowired
	PetRepo petRepo;
	
	@Autowired
	MongoTemplate mongoTemplate;

	public Pet createPet(Pet pet) {
		
		 if (pet.getId() != null) {
	            Pet existingUser =mongoTemplate.findById(pet.getId(), Pet.class);
	            if (existingUser != null) {
	                throw new CustomException("Pet with ID " + pet.getId() + " already exists.");
	            }
	        }
		
		return petRepo.createPet(pet);
	}

	public Pet getPetById(Integer id) {
		
		return petRepo.getPetById(id);
	}

	public boolean deletePet(Integer id) {
		 if (id != null) {
	            Pet existingUser = mongoTemplate.findById(id, Pet.class);
	            if (existingUser == null) {
	                throw new CustomException("Pet with ID " + id + " not found.");
	            }
	        }
		return petRepo.deletePet(id);
	}

	

	public Pet updatePet(Integer id, Pet updatedpet) {
		if (id != null) {
            Pet existingUser = mongoTemplate.findById(id, Pet.class);
            if (existingUser == null) {
                throw new CustomException("Pet with ID " + id + " not found.");
            }
        }
		return petRepo.updatePet(id,updatedpet);
	}
	
	
	

}
