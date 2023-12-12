import java.util.ArrayList;
import java.util.Date;

//块的基本数据
public class Block {
    public String hash;
    public String previousHash;
    public String merkleRoot;
    public ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private long timeStamp; //as number of milliseconds since 1/1/1970.
    private int nonce;

    public Block(String previousHash) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();

        this.hash = calculateHash();
    }

    //应用SHA256计算Hash值
    public String calculateHash() {
        String calculatedHash = StringUtil.applySHA256(previousHash +
                Long.toString(timeStamp) + Integer.toString(nonce) + merkleRoot);
        return calculatedHash;
    }

    public void mineBlock(int difficulty) {

        merkleRoot = StringUtil.getMerkleRoot(transactions);
        String target = StringUtil.getDifficultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mines!" + hash);
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction == null) return false;
        if (previousHash != "0") {
            if (transaction.processTransaction() != true) {
                System.out.println("Transaction failed to process. Discarded.");
                return false;
            }
        }
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }

}

