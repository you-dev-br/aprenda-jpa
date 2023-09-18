package aprenda.jpa.pessoa;

import aprenda.jpa.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class PessoaItems {
    @EmbeddedId
    private PessoaItemsId id;

    @ManyToOne
    @MapsId("pessoaId")
    private Pessoa pessoa;

    @ManyToOne
    @MapsId("itemId")
    private Item item;

    private OffsetDateTime dataDoEmprestimo;

    public PessoaItems(Pessoa pessoa, Item item, OffsetDateTime dataDoEmprestimo) {
        this.pessoa = pessoa;
        this.item = item;
        this.dataDoEmprestimo = dataDoEmprestimo;
        this.id = new PessoaItemsId(pessoa.getId(), item.getId());
    }
}
