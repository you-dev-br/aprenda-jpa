package aprenda.jpa;

import aprenda.jpa.categoria.Categoria;
import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.item.QrCode;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * - Aprenda JPA 05 - Relacionamento MuitosParaMuitos (@ManyToMany)
 */
@SpringBootTest
class Aula05 {
    private static final String NOME = "Epson LX300";
    private static final String DESCRICAO = "Impressora matricial. Papel continuo ou folha individual.";
    private static final String CATEGORIA_NOME = "Impressora";

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void salvarItemComCategorias_Entao_VerificarNoRepositorio() {
        val novoItem = new Item();
        novoItem.setNome(NOME);
        novoItem.setDescricao(DESCRICAO);

        val novaCategoria = new Categoria(CATEGORIA_NOME);
        novoItem.getCategorias().add(novaCategoria);

        itemRepository.save(novoItem);

        val itemNoRepositorio = itemRepository.findById(novoItem.getId()).orElse(null);
        assertNotNull(itemNoRepositorio);

        val categoriaNoRepositorio = itemNoRepositorio.getCategorias().stream().findFirst().orElse(null);
        assertNotNull(categoriaNoRepositorio);
        assertEquals(CATEGORIA_NOME, categoriaNoRepositorio.getNome());
    }
}