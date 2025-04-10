import co.edu.uniquindio.CommuSafe.modules.category.model.Category;
import co.edu.uniquindio.CommuSafe.modules.category.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    public void registrarTest() {
        Category category = new Category();
        category.setName("Seguridad");
        category.setIcon("security-icon");

        Category categoryCreated = categoryRepository.save(category);

        assertNotNull(categoryCreated);
        assertNotNull(categoryCreated.getId());

        System.out.println("Categoría creada con ID: " + categoryCreated.getId());
    }

    @Test
    public void actualizarTest() {
        Category category = new Category();
        category.setName("Infraestructura");
        category.setIcon("infrastructure-icon");

        Category savedCategory = categoryRepository.save(category);
        String categoryId = savedCategory.getId().toString();

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        assertTrue(optionalCategory.isPresent(), "La categoría debe existir para actualizarla.");

        Category categoryToUpdate = optionalCategory.get();
        categoryToUpdate.setIcon("updated-infrastructure-icon");

        categoryRepository.save(categoryToUpdate);

        Category updatedCategory = categoryRepository.findById(categoryId).orElseThrow();
        assertEquals("updated-infrastructure-icon", updatedCategory.getIcon());
    }

    @Test
    public void listarTodasTest() {
        categoryRepository.save(new Category() {{ setName("Seguridad"); setIcon("security-icon"); }});
        categoryRepository.save(new Category() {{ setName("Emergencias Médicas"); setIcon("medical-icon"); }});
        categoryRepository.save(new Category() {{ setName("Infraestructura"); setIcon("infrastructure-icon"); }});

        List<Category> categoryList = categoryRepository.findAll();

        assertFalse(categoryList.isEmpty(), "La lista de categorías no debe estar vacía.");
        assertEquals(3, categoryList.size(), "Debe haber tres categorías en la base de datos.");
    }

    @Test
    public void eliminarTest() {
        Category category = new Category();
        category.setName("Categoría Temporal");
        category.setIcon("temp-icon");

        Category savedCategory = categoryRepository.save(category);
        String categoryId = savedCategory.getId().toString();

        categoryRepository.deleteById(categoryId);

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        assertTrue(optionalCategory.isEmpty(), "La categoría debería haber sido eliminada.");
    }

    @Test
    public void buscarPorNombreTest() {
        String uniqueName = "Categoría Única " + System.currentTimeMillis();
        Category category = new Category();
        category.setName(uniqueName);
        category.setIcon("unique-icon");

        categoryRepository.save(category);

        Optional<Category> foundCategory = categoryRepository.findByName(uniqueName);

        assertTrue(foundCategory.isPresent(), "La categoría debe existir en la base de datos.");
        assertEquals(uniqueName, foundCategory.get().getName(), "El nombre de la categoría debe coincidir.");
    }
}
