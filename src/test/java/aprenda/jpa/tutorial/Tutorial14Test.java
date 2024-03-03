package aprenda.jpa.tutorial;

import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class Tutorial14Test {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private Tutorial14Transactional tutorial14Transactional;

    @Test
    void fetchTypeLazy() {
        var pessoa = new Pessoa();
        pessoaRepository.save(pessoa);
        var itemsDaPessoa = tutorial14Transactional.numeroDeItemsDaPessoa(pessoa.getId());
        assertEquals(0, itemsDaPessoa);
    }

    @Test
    void rollbackQuandoTemosExcecao() {
        try {
            tutorial14Transactional
                    .salvarCategoriaEItem("Brinquedo", null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        var categoria = categoriaRepository.findById("Brinquedo");
        assertTrue(categoria.isEmpty());
    }

    @Test
    void armadilhaAtualizarEntidades() {
        var item = new Item();
        item.setNome("Aquaplay");
        itemRepository.save(item);

        tutorial14Transactional.alterar(item.getId());

        var itemDoRepositorio = itemRepository
                .findById(item.getId())
                .orElseThrow();
        assertEquals("Alterado", itemDoRepositorio.getNome());
    }

    @Test
    void armadilhaMetodoPrivado() {
        try {
            tutorial14Transactional
                    .salvarCategoriaEItemComMetodoPrivado("Brinquedo", null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        var categoria = categoriaRepository.findById("Brinquedo");
        assertTrue(categoria.isEmpty());
    }
}
