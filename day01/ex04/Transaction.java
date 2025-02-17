import java.util.UUID;

public class Transaction {

    public Transaction(User sender, User recipient, Integer transfer_amount) {
        this.identifier = UUID.randomUUID();
        setSender(sender);
        setRecipient(recipient);
        setTransferAmount(transfer_amount);
    }

    public Transaction(Transaction other) {
        this.identifier = other.getId();
        setSender(other.getRecipient());
        setRecipient(other.getSender());
        setTransferAmount(-other.getTransferAmount());
    }

    public enum Transfer {
        DEBIT, CREDIT
    }

    private final UUID identifier;
    private User recipient;
    private User sender;
    private Transfer type;
    private Integer transferAmount;

    public UUID getId() {
        return identifier;
    }

    public User getSender() {
        return sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public Integer getTransferAmount() {
        return transferAmount;
    }

    public Transfer getTypeTransfer() {
        return type;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public void setTransferAmount(Integer transfer_amount) {
        if (transfer_amount < 0) {
            type = Transfer.CREDIT;
        } else {
            type = Transfer.DEBIT;
        }
        this.transferAmount = transfer_amount;
    }
}