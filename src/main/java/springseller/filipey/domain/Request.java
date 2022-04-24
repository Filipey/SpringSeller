package springseller.filipey.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import java.util.Set;

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
    private Set<ProductRequest> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<ProductRequest> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductRequest> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", requestDate=" + requestDate +
                ", amount=" + amount +
                '}';
    }
}
