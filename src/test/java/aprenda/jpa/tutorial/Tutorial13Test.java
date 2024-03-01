package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Tutorial13Test {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void fetchType() {
        var item = new Item();
        item.setNome("Pega Varetas");
        itemRepository.save(item);

        var categoriasDoRepositorio = itemRepository
                .findById(item.getId())
                .map(Item::getCategorias)
                .orElse(Set.of());

        assertTrue(categoriasDoRepositorio.isEmpty());
    }
}
