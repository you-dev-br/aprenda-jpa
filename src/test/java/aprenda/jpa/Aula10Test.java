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
 * Aprenda JPA 10 - Busca avancada: Criteria API
 */
@SpringBootTest
class Aula10Test {
    private static final String PESSOA_1_NOME = "Daniela";
    private static final String PESSOA_2_NOME = "Danilo";
    private static final String PESSOA_3_NOME = "Dilma";
    private static final String PESSOA_4_NOME = "Dolores";
    private static final String PESSOA_5_NOME = "Doria";
    private static final String ITEM_NOME = "Danoninho";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void buscarPessoaPeloNome() {
        salvarPessoa(PESSOA_1_NOME);

        val criteriaBuilder = entityManager.getCriteriaBuilder();
        val query = criteriaBuilder.createQuery(Pessoa.class);
        val pessoa = query.from(Pessoa.class);
        val nomeEquals = criteriaBuilder.equal(pessoa.get("nome"), PESSOA_1_NOME);

        val pessoaNoRepositorio = entityManager.createQuery(query.where(nomeEquals)).getSingleResult();
        assertNotNull(pessoaNoRepositorio);
        assertEquals(PESSOA_1_NOME, pessoaNoRepositorio.getNome());
    }

    @Test
    void buscarPessoasPeloNome() {
        salvarPessoa(PESSOA_2_NOME);
        salvarPessoa(PESSOA_3_NOME);

        val criteriaBuilder = entityManager.getCriteriaBuilder();
        val query = criteriaBuilder.createQuery(Pessoa.class);
        val pessoa = query.from(Pessoa.class);
        val nomesIn = criteriaBuilder.in(pessoa.get("nome")).value(PESSOA_2_NOME).value(PESSOA_3_NOME);
        val ordenadoPeloNome = criteriaBuilder.desc(pessoa.get("nome"));

        val pessoasNoRepositorio = entityManager
                .createQuery(query.where(nomesIn).orderBy(ordenadoPeloNome))
                .getResultList();

        assertNotNull(pessoasNoRepositorio);
        assertEquals(2, pessoasNoRepositorio.size());
        assertEquals(PESSOA_2_NOME, pessoasNoRepositorio.get(1).getNome());
        assertEquals(PESSOA_3_NOME, pessoasNoRepositorio.get(0).getNome());
    }


    @Test
    void buscarPorArgumentos() {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(PESSOA_4_NOME);
        val novoItem = new Item();
        novoItem.setNome(ITEM_NOME);
        novaPessoa.getItems().add(novoItem);
        pessoaRepository.save(novaPessoa);

        val buscaPeloNomePessoa = PESSOA_4_NOME;
        val buscaPeloNomeItem = ITEM_NOME;

        val criteriaBuilder = entityManager.getCriteriaBuilder();
        val query = criteriaBuilder.createQuery(Pessoa.class);
        val pessoa = query.from(Pessoa.class);

        val criterioDeBusca = new ArrayList<Predicate>();
        if (buscaPeloNomePessoa != null) {
            criterioDeBusca.add(criteriaBuilder.equal(pessoa.get("nome"), PESSOA_4_NOME));
        }
        if (buscaPeloNomeItem != null) {
            val items = pessoa.join("items");
            criterioDeBusca.add(criteriaBuilder.equal(items.get("nome"), buscaPeloNomeItem));
        }

        val pessoasNoRepositorio = entityManager
                .createQuery(query.where(criterioDeBusca.toArray(new Predicate[0])))
                .getResultList();

        assertNotNull(pessoasNoRepositorio);
        assertEquals(1, pessoasNoRepositorio.size());
        assertEquals(PESSOA_4_NOME, pessoasNoRepositorio.get(0).getNome());
    }

    private void salvarPessoa(String nome) {
        val novaPessoa = new Pessoa();
        novaPessoa.setNome(nome);
        pessoaRepository.save(novaPessoa);
    }
}