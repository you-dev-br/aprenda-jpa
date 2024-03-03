package aprenda.jpa.tutorial;

import aprenda.jpa.categoria.Categoria;
import aprenda.jpa.categoria.CategoriaRepository;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.PessoaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Tutorial14Transactional {
    private final CategoriaRepository categoriaRepository;
    private final ItemRepository itemRepository;
    private final PessoaRepository pessoaRepository;

    @Transactional
    public int numeroDeItemsDaPessoa(Integer pessoaId) {
        return pessoaRepository
                .findById(pessoaId)
                .map(pessoa -> pessoa.getItems().size())
                .orElse(0);
    }

    @Transactional
    public void salvarCategoriaEItem(String nomeDaCategoria, String nomeDoItem) {
        var categoria = new Categoria(nomeDaCategoria);
        categoriaRepository.save(categoria);

        var item = new Item();
        item.setNome(nomeDoItem);
        itemRepository.save(item);
    }

    @Transactional
    public void alterar(Integer itemId) {
        var itemParaAlterar = itemRepository
                .findById(itemId)
                .orElseThrow();
        itemParaAlterar.setNome("Alterado");
    }

    @Transactional
    public void salvarCategoriaEItemComMetodoPrivado(String nomeDaCategoria, String nomeDoItem) {
        salvarCategoriaEItemPrivado(nomeDaCategoria, nomeDoItem);
    }

    private void salvarCategoriaEItemPrivado(String nomeDaCategoria, String nomeDoItem) {
        var categoria = new Categoria(nomeDaCategoria);
        categoriaRepository.save(categoria);

        var item = new Item();
        item.setNome(nomeDoItem);
        itemRepository.save(item);
    }
}
