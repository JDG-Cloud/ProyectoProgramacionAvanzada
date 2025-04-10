package co.edu.uniquindio.CommuSafe.modules.category.repository;

import co.edu.uniquindio.CommuSafe.modules.category.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

    Optional<Category> findByName(String name);
    Boolean existsByName(String name);
    List<Category> findAllByOrderByNameAsc();
    void deleteById(String id);
    @Query("{ 'name' : ?0 }")
    void deleteCategoryByName(String name);

}
