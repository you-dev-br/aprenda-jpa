package aprenda.jpa;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Aprenda JPA 02 - Salvar e buscar por chave primaria
 */
@SpringBootTest
public class Aula02Test {
    private static final String ITEM_NOME = "Epson LX300";
    private static final String ITEM_DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";
    private static final String ITEM_NOME_ATUALIZADO = "Epson LX300 - Atualizado";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarItem() {
        val item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        itemRepository.save(item);

        val itemDoRepositorio = itemRepository.findById(item.getId()).orElse(null);
        assertNotNull(itemDoRepositorio);
        assertEquals(item.getId(), itemDoRepositorio.getId());
        assertEquals(ITEM_NOME, itemDoRepositorio.getNome());
        assertEquals(ITEM_DESCRICAO, itemDoRepositorio.getDescricao());
    }

    @Test
    void atualizarItem() {
        val item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        itemRepository.save(item);

        itemRepository.findById(item.getId())
                .ifPresent(itemParaAtualizar -> {
                    itemParaAtualizar.setNome(ITEM_NOME_ATUALIZADO);
                    itemRepository.save(itemParaAtualizar);
                });

        val itemDoRepositorio = itemRepository.findById(item.getId()).orElse(null);
        assertNotNull(itemDoRepositorio);
        assertEquals(ITEM_NOME_ATUALIZADO, itemDoRepositorio.getNome());
        assertEquals(ITEM_DESCRICAO, itemDoRepositorio.getDescricao());
    }
}
