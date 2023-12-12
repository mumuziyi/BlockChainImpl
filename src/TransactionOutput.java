import java.security.PublicKey;

public class TransactionOutput {
    public String id;
    public PublicKey recipient;//also known as the new owner of these coins.
    public float value;//the amount of coins they own
    public String parentTransactionID;//the id of the transaction this output was created in

    public TransactionOutput(PublicKey recipient, float value, String parentTransactionID) {
        this.recipient = recipient;
        this.value = value;
        this.parentTransactionID = parentTransactionID;
        this.id = StringUtil.applySHA256(StringUtil.getStringFromKey(recipient) + Float.toString(value) +
                parentTransactionID);
    }

    public boolean isMine(PublicKey publicKey) {
        return (publicKey == recipient);
    }
}
