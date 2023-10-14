package aprenda.jpa.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class QrCode {
    @Id
    @GeneratedValue
    private Integer id;
    private String codigo;
}
