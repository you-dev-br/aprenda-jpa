package aprenda.jpa.item;

import aprenda.jpa.categoria.Categoria;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(nullable = false)
    private String nome;
    private String descricao;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private QrCode qrCode;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Categoria> categorias = new HashSet<>();

    /*
     * Podemos utilizar a anotacao @Type our @Convert. Exemplos:
     * @Type(JsonType.class)
     * ou
     * @Convert(converter = DetalhesConverter.class)
     */
    @Convert(converter = DetalhesConverter.class)
    @Column(columnDefinition = "json")
    private Detalhes detalhes;
}