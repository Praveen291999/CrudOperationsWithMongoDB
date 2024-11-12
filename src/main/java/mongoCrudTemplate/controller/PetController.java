package mongoCrudTemplate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mongoCrudTemplate.entity.Pet;
import mongoCrudTemplate.service.PetService;

@RestController
public class PetController {

	@Autowired
	PetService petService;

	@PostMapping("/createPet")
	public Pet createPet(@RequestBody Pet pet) {
		return petService.createPet(pet);
	}

	@GetMapping("/get/{id}")
	public Pet getPetById(@PathVariable Integer id) {
		return petService.getPetById(id);
	}

	@DeleteMapping("delete/{id}")
	public boolean deletePet(@PathVariable Integer id) {
		return petService.deletePet(id);
	}

	@PutMapping("update/{id}")
	public Pet updatePet(@PathVariable Integer id, @RequestBody Pet updatedpet) {
		return petService.updatePet(id, updatedpet);
	}

}
