package aprenda.jpa.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Item {
    @Id
    @GeneratedValue
    private Integer id;
    private String descricao;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private QrCode qrCode;
}
