package back.model;

import java.util.UUID;

public class Card {
    private final UUID id = UUID.randomUUID();
    private String cardNumber;
    private int cardPassword;
    private Double balance;
    private UUID userId;
    private boolean isActive;

    public Card(String cardNumber, int cardPassword, Double balance, UUID userId, boolean isActive) {
        this.cardNumber = cardNumber;
        this.cardPassword = cardPassword;
        this.balance = balance;
        this.userId = userId;
        this.isActive = isActive;
    }

    public UUID getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCardPassword() {
        return cardPassword;
    }

    public void setCardPassword(int cardPassword) {
        this.cardPassword = cardPassword;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

}
