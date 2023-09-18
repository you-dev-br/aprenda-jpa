package aprenda.jpa;

import aprenda.jpa.categoria.Categoria;
import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Aprenda JPA 05 - Relacionamento MuitosParaMuitos (@ManyToMany)
 */
@SpringBootTest
class Tutorial05Test {
    private static final String ITEM_NOME = "Sony Walkman";
    private static final String CATEGORIA_NOME = "Audio";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void salvarItemComCategorias_Entao_VerificarQueItemFoiSalvo() {
        val item = new Item();
        item.setNome(ITEM_NOME);

        val categoria = new Categoria(CATEGORIA_NOME);
        item.getCategorias().add(categoria);

        itemRepository.save(item);

        val itemDoRepositorio = itemRepository.findById(item.getId()).orElse(null);
        assertNotNull(itemDoRepositorio);

        val categoriaDoRepositorio = itemDoRepositorio.getCategorias().stream().findFirst().orElse(null);
        assertNotNull(categoriaDoRepositorio);
        assertEquals(CATEGORIA_NOME, categoriaDoRepositorio.getNome());
    }
}