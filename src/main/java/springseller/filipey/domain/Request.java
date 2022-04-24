package springseller.filipey.domain;

import sun.util.resources.LocaleData;

import java.math.BigDecimal;

public class Request {

    private Long id;
    private Client client;
    private LocaleData requestDate;
    private BigDecimal amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocaleData getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocaleData requestDate) {
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
}
