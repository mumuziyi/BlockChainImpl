public class TransactionInput {
    //Reference to TransactionOutputs -> transactionId
    public String transactionOutputID;
    //Contains the Unspent transaction output
    public TransactionOutput UTXO;

    public TransactionInput(String transactionOutputID) {
        this.transactionOutputID = transactionOutputID;
    }
}
