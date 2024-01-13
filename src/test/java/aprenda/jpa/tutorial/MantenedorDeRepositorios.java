package aprenda.jpa.tutorial;

import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MantenedorDeRepositorios {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ItemRepository itemRepository;

    /**
     * Remove todas as entidades de todos os repositorios
     */
    void apagarTudo() {
        categoriaRepository.deleteAll();
        pessoaRepository.deleteAll();
        itemRepository.deleteAll();
    }
}
