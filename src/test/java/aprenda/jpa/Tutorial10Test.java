package aprenda.jpa;

import aprenda.jpa.pessoa.Pessoa;
import aprenda.jpa.pessoa.PessoaRepository;
import lombok.val;
import aprenda.jpa.item.Item;
import aprenda.jpa.item.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Aprenda JPA 10 - Busca avancada: @Query
 */
@SpringBootTest
class Tutorial10Test {
    private static final String PESSOA_NOME = "Gabriela Oliveira";

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    void buscarPessoaPeloNomeContendo() {
        pessoaRepository.save(new Pessoa(PESSOA_NOME));
        List<Pessoa> pessoasContendoOliveira = pessoaRepository.buscarPorNomeContendo("Oliveira");
        assertEquals(1, pessoasContendoOliveira.size());
        assertEquals(PESSOA_NOME, pessoasContendoOliveira.get(0).getNome());
    }
}