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
 * - Aprenda JPA 02 - Salvar e buscar por chave primaria
 */
@SpringBootTest
public class Aula02 {
    private static final String NOME = "Epson LX300";
    private static final String DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";
    private static final String NOME_ATUALIZADO = "Epson LX300 - Atualizado";

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void salvarUmNovoItem_Entao_VerificarItemNoRepositorio() {
        val novoItem = new Item();
        novoItem.setNome(NOME);
        novoItem.setDescricao(DESCRICAO);
        itemRepository.save(novoItem);

        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);
        assertEquals(novoItem.getId(), itemNoRepositorio.getId());
        assertEquals(NOME, itemNoRepositorio.getNome());
        assertEquals(DESCRICAO, itemNoRepositorio.getDescricao());
    }

    @Test
    void atualizarUmItemExistente_Entao_VerificarQueItemFoiAtualizadoNoRepositorio() {
        val novoItem = new Item();
        novoItem.setNome(NOME);
        novoItem.setDescricao(DESCRICAO);
        itemRepository.save(novoItem);

        itemRepository.findById(novoItem.getId())
                .ifPresent(itemParaAtualizar -> {
                    itemParaAtualizar.setNome(NOME_ATUALIZADO);
                    itemRepository.save(itemParaAtualizar);
                });

        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);
        assertEquals(NOME_ATUALIZADO, itemNoRepositorio.getNome());
        assertEquals(DESCRICAO, itemNoRepositorio.getDescricao());
    }
}
