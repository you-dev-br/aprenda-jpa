package aprenda.jpa;

import lombok.val;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Aprenda JPA 10 - Busca avancada: @Query
 */
@SpringBootTest
class Tutorial10Test {
    private static final String ITEM_NOME = "Kichute";
    private static final String ITEM_DESCRICAO = "Tenis muito popular entre os meninos da decade de oitenta";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void buscarItemPelaDescricaoContendo() {
        val item = new Item(ITEM_NOME, ITEM_DESCRICAO);
        itemRepository.save(item);

        List<Item> buscaPorNoventa = itemRepository.buscarDescricaoContendo("noventa");
        assertTrue(buscaPorNoventa.isEmpty());

        List<Item> buscaPorOitenta = itemRepository.buscarDescricaoContendo("oitenta");
        assertEquals(1, buscaPorOitenta.size());
        assertEquals(item.getId(), buscaPorOitenta.get(0).getId());
    }
}