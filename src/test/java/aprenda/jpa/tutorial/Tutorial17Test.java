package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.item.NumeroDeSerie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tutorial17Test {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void entidadeEmbutida() {
        var item = new Item();
        item.setNome("Maquina de pipoca");
        item.setNumeroDeSerie(new NumeroDeSerie("ELE", "BC2970CR", 1));
        itemRepository.save(item);

        var itemDoRepositorio = itemRepository
                .findById(item.getId())
                .orElseThrow();
        assertEquals("Maquina de pipoca", itemDoRepositorio.getNome());
        assertEquals(
                new NumeroDeSerie("ELE", "BC2970CR", 1),
                item.getNumeroDeSerie());
    }
}
