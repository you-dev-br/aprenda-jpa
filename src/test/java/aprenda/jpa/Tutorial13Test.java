package aprenda.jpa;

import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import lombok.val;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Tutorial13Test {
    private static final String PESSOA_1_NOME = "Ana Silva";
    private static final String PESSOA_2_NOME = "Beatriz Silva";
    private static final String PESSOA_3_NOME = "Cristiana Silva";
    private static final String PESSOA_4_NOME = "Diana Silva";
    private static final String PESSOA_5_NOME = "Eduardo Silva";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void buscarPessoaPeloNomeContendoParteDoNomeComPaginacao() {
        pessoaRepository.save(new Pessoa(PESSOA_1_NOME));
        pessoaRepository.save(new Pessoa(PESSOA_2_NOME));
        pessoaRepository.save(new Pessoa(PESSOA_3_NOME));
        pessoaRepository.save(new Pessoa(PESSOA_4_NOME));
        pessoaRepository.save(new Pessoa(PESSOA_5_NOME));

        val paginaZero = pessoaRepository.findByNomeContaining("Silva", PageRequest.of(0, 2, Sort.by("nome")));
        assertEquals(5, paginaZero.getTotalElements());
        assertEquals(3, paginaZero.getTotalPages());
        assertEquals(2, paginaZero.getContent().size());
        assertEquals(PESSOA_1_NOME, paginaZero.getContent().get(0).getNome());
        assertEquals(PESSOA_2_NOME, paginaZero.getContent().get(1).getNome());

        val paginaDois = pessoaRepository.findByNomeContaining("Silva", PageRequest.of(2, 2, Sort.by("nome")));
        assertEquals(5, paginaDois.getTotalElements());
        assertEquals(3, paginaDois.getTotalPages());
        assertEquals(1, paginaDois.getContent().size());
        assertEquals(PESSOA_5_NOME, paginaDois.getContent().get(0).getNome());
    }
}