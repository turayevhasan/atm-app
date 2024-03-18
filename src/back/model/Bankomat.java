package back.model;

import java.util.UUID;

public class Bankomat {
    private final UUID id = UUID.randomUUID();
    private String name;
    private float percentTransaction;

    public Bankomat(String name, float percentTransaction) {
        this.name = name;
        this.percentTransaction = percentTransaction;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPercentTransaction() {
        return percentTransaction;
    }

    public void setPercentTransaction(float percentTransaction) {
        this.percentTransaction = percentTransaction;
    }
}
