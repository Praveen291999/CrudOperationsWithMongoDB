package mongoCrudTemplate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.DeleteResult;

import mongoCrudTemplate.entity.Pet;
import mongoCrudTemplate.exception.CustomException;

@Repository
public class PetRepo {

	@Autowired
	MongoTemplate mongoTemplate;

	public Pet createPet(Pet pet) {

		return mongoTemplate.insert(pet);
	}

	public Pet getPetById(Integer id) {
		Query query = new Query(Criteria.where("id").is(id));
		Pet pet = mongoTemplate.findOne(query, Pet.class);

		if (pet == null) {
			throw new CustomException("Pet with ID " + id + " not found");
		}

		return pet;
	}

	public boolean deletePet(Integer id) {
		Query query = new Query(Criteria.where("id").is(id));
	    DeleteResult result= mongoTemplate.remove(query, Pet.class);
		return result.wasAcknowledged() && result.getDeletedCount() > 0;
	}

	public Pet updatePet(Integer id, Pet updatedPet) {
		 Query query = new Query(Criteria.where("id").is(id));
	        Update update = new Update()
	            .set("name", updatedPet.getName())
	            .set("price", updatedPet.getPrice());

	        mongoTemplate.updateFirst(query, update, Pet.class);
	        return updatedPet;
		
	}

}
