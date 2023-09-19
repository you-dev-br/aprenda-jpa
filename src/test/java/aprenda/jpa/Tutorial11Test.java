package aprenda.jpa;

import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.Predicate;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Aprenda JPA 11 - Busca avancada: Criteria API
 */
@SpringBootTest
class Tutorial11Test {
    private static final String PESSOA_1_NOME = "Daniela";
    private static final String PESSOA_2_NOME = "Danilo";
    private static final String PESSOA_3_NOME = "Dilma";
    private static final String PESSOA_4_NOME = "Dolores";
    private static final String ITEM_NOME = "Danoninho";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void buscarPessoaPeloNome() {
        pessoaRepository.save(new Pessoa(PESSOA_1_NOME));

        val criteriaBuilder = entityManager.getCriteriaBuilder();
        val query = criteriaBuilder.createQuery(Pessoa.class);
        val pessoa = query.from(Pessoa.class);
        val nomeEquals = criteriaBuilder.equal(pessoa.get("nome"), PESSOA_1_NOME);

        val pessoaDoRepositorio = entityManager.createQuery(query.where(nomeEquals)).getSingleResult();
        assertNotNull(pessoaDoRepositorio);
        assertEquals(PESSOA_1_NOME, pessoaDoRepositorio.getNome());
    }

    @Test
    void buscarPessoasPeloNome() {
        pessoaRepository.save(new Pessoa(PESSOA_2_NOME));
        pessoaRepository.save(new Pessoa(PESSOA_3_NOME));

        val criteriaBuilder = entityManager.getCriteriaBuilder();
        val query = criteriaBuilder.createQuery(Pessoa.class);
        val from = query.from(Pessoa.class);
        val nomesIn = criteriaBuilder.in(from.get("nome")).value(PESSOA_2_NOME).value(PESSOA_3_NOME);
        val ordenadoPeloNome = criteriaBuilder.desc(from.get("nome"));

        val pessoasDoRepositorio = entityManager
                .createQuery(query.where(nomesIn).orderBy(ordenadoPeloNome))
                .getResultList();

        assertNotNull(pessoasDoRepositorio);
        assertEquals(2, pessoasDoRepositorio.size());
        assertEquals(PESSOA_2_NOME, pessoasDoRepositorio.get(1).getNome());
        assertEquals(PESSOA_3_NOME, pessoasDoRepositorio.get(0).getNome());
    }


    @Test
    void buscarPorArgumentos() {
        val pessoa = new Pessoa();
        pessoa.setNome(PESSOA_4_NOME);
        val item = new Item();
        item.setNome(ITEM_NOME);
        itemRepository.save(item);
        pessoa.getItems().add(item);
        pessoaRepository.save(pessoa);

        val buscaPeloNomePessoa = PESSOA_4_NOME;
        val buscaPeloNomeItem = ITEM_NOME;

        val criteriaBuilder = entityManager.getCriteriaBuilder();
        val query = criteriaBuilder.createQuery(Pessoa.class);
        val from = query.from(Pessoa.class);

        val criterioDeBusca = new ArrayList<Predicate>();
        if (buscaPeloNomePessoa != null) {
            criterioDeBusca.add(criteriaBuilder.equal(from.get("nome"), PESSOA_4_NOME));
        }
        if (buscaPeloNomeItem != null) {
            val items = from.join("items");
            criterioDeBusca.add(criteriaBuilder.equal(items.get("nome"), buscaPeloNomeItem));
        }

        val pessoasDoRepositorio = entityManager
                .createQuery(query.where(criterioDeBusca.toArray(new Predicate[0])))
                .getResultList();

        assertNotNull(pessoasDoRepositorio);
        assertEquals(1, pessoasDoRepositorio.size());
        assertEquals(PESSOA_4_NOME, pessoasDoRepositorio.get(0).getNome());
    }
}