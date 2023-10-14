package aprenda.jpa.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private String descricao;
    @OneToOne
    private QrCode qrCode;
}
