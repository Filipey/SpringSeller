package springseller.filipey.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "REQUEST")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    @Column(name = "REQUEST_DATE")
    private LocalDate requestDate;

    @Column(name = "AMOUNT", precision = 20, scale = 2)
    private BigDecimal amount;

    @OneToMany(mappedBy = "request")
    private List<ProductRequest> products;

}
