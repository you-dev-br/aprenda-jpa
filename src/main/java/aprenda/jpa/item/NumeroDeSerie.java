package aprenda.jpa.item;

import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class NumeroDeSerie {
    @Column(name = "numero_de_serie_grupo")
    private String grupo;
    @Column(name = "numero_de_serie_modelo")
    private String modelo;
    @Column(name = "numero_de_serie_numero")
    private Integer numero;
}
