package aprenda.jpa.pessoa;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class PessoaItemsId {
    private Integer pessoaId;
    private Integer itemId;
}
