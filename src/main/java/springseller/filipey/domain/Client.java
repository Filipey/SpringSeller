package springseller.filipey.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CLIENT")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    @NotEmpty(message = "Name field is required")
    private String name;

    @Column(name = "CPF", length = 11)
    @NotEmpty(message = "CPF field is required")
    @CPF(message = "Invalid CPF")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Request> requests;


}
