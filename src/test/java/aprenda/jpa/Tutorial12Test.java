package aprenda.jpa;

import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;
import static org.springframework.data.domain.ExampleMatcher.matching;

/**
 * Aprenda JPA 11 - Busca avancada: Criteria API
 */
@SpringBootTest
class Tutorial12Test {
    private static final String PESSOA_1_NOME = "Eduardo";
    private static final String PESSOA_2_NOME = "Elisa";
    private static final String PESSOA_3_NOME = "Fabiano";
    private static final String PESSOA_4_NOME = "Gomes";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void buscarTodosPorExemplo() {
        pessoaRepository.save(new Pessoa(PESSOA_1_NOME));
        pessoaRepository.save(new Pessoa(PESSOA_2_NOME));

        val exemplo = new Pessoa(PESSOA_2_NOME);
        val exemplosNoRepositorio = pessoaRepository.findAll(Example.of(exemplo));

        assertEquals(1, exemplosNoRepositorio.size());
        assertEquals(PESSOA_2_NOME, exemplosNoRepositorio.get(0).getNome());
    }

    @Test
    void buscarUmPorExemplo() {
        pessoaRepository.save(new Pessoa(PESSOA_3_NOME));
        pessoaRepository.save(new Pessoa(PESSOA_4_NOME));

        val matcher = matching()
                .withMatcher("nome", startsWith().ignoreCase());

        val exemplo = new Pessoa("f");
        val exemplosNoRepositorio = pessoaRepository.findAll(Example.of(exemplo, matcher));

        assertEquals(1, exemplosNoRepositorio.size());
        assertEquals(PESSOA_3_NOME, exemplosNoRepositorio.get(0).getNome());
    }

}