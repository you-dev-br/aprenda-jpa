package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class Tutorial02Test {
    private static final String ITEM_NOME = "Impressora";
    private static final String ITEM_DESCRICAO = "Epson LX300";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void criarItem() {
        var item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        var itemDoRepositorio = itemRepository.save(item);
        assertNotNull(itemDoRepositorio.getId());
    }

    @Test
    void alterarItem() {
        var nomeAtualizado = ITEM_NOME + " Atualizado";
        var item = new Item();
        item.setNome(ITEM_NOME);
        item.setDescricao(ITEM_DESCRICAO);
        itemRepository.save(item);

        itemRepository.findById(item.getId())
                .ifPresent(itemParaAtualizar -> {
                    itemParaAtualizar.setNome(nomeAtualizado);
                    itemRepository.save(itemParaAtualizar);
                });

        itemRepository.findById(item.getId())
                .ifPresentOrElse(itemParaVerificar ->
                    assertEquals(nomeAtualizado, itemParaVerificar.getNome())
                , Assertions::fail);
    }
}
