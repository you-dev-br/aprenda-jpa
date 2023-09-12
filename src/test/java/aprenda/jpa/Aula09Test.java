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
 * - Aprenda JPA 09 - Busca avancada: @Query
 */
@SpringBootTest
class Aula09Test {
    private static final String ITEM_NOME = "Kichute";
    private static final String ITEM_DESCRICAO = "Tenis muito popular entre os meninos da decade de oitenta";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarUmaNovoItem_Entao_BuscarDescricaoContendo() {
        val novoItem = new Item();
        novoItem.setNome(ITEM_NOME);
        novoItem.setDescricao(ITEM_DESCRICAO);

        itemRepository.save(novoItem);

        List<Item> buscaPorNoventa = itemRepository.buscarDescricaoContendo("noventa");
        assertTrue(buscaPorNoventa.isEmpty());

        List<Item> buscaPorOitenta = itemRepository.buscarDescricaoContendo("oitenta");
        assertEquals(1, buscaPorOitenta.size());
        assertEquals(novoItem.getId(), buscaPorOitenta.get(0).getId());
    }
}