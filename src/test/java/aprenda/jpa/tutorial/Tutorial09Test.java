package aprenda.jpa.tutorial;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class Tutorial09Test {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private EntityManager em;

    @Test
    void buscaPorEmail() {
        var pessoa = new Pessoa();
        pessoa.setNome("Daniel");
        pessoa.setEmail("daniel@test.com");
        pessoaRepository.save(pessoa);

        var cb = em.getCriteriaBuilder();
        var q = cb.createQuery(Pessoa.class);
        var p = q.from(Pessoa.class);
        var equalsEmail = cb.equal(p.get("email"), "daniel@test.com");
        var selectPessoaPorEmail = q.select(p).where(equalsEmail);
        var pessoaDaBusca = em.createQuery(selectPessoaPorEmail)
                .getSingleResult();

        assertEquals("daniel@test.com", pessoaDaBusca.getEmail());
    }

    @Test
    void buscaPeloNomeDoItem() {
        var item = new Item();
        item.setNome("Pogobol");
        itemRepository.save(item);

        var pessoa = new Pessoa();
        pessoa.setNome("Danilo");
        pessoa.setEmail("danilo@test.com");
        pessoa.getItems().add(item);
        pessoaRepository.save(pessoa);

        var cb = em.getCriteriaBuilder();
        var q = cb.createQuery(Pessoa.class);
        var p = q.from(Pessoa.class);
        var i = p.join("items");
        var equalsNomeDoItem = cb.equal(i.get("nome"), "Pogobol");
        var selectPessoasPeloNomeDoItem = q.where(equalsNomeDoItem).select(p);

        var pessoasDaBusca = em.createQuery(selectPessoasPeloNomeDoItem).getResultList();
        assertEquals(1, pessoasDaBusca.size());
        assertEquals(pessoa.getId(), pessoasDaBusca.get(0).getId());
    }
}
