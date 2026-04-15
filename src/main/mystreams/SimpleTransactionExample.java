package main.mystreams;

import java.util.ArrayList;
import java.util.List;

public class SimpleTransactionExample {

    // the items to be stored in the database. In this case the names.
    private static List<String> database = new ArrayList<>();

    public static void main(String[] args) {
        TransactionManager txnManager = new TransactionManager();
        txnManager.manageTransaction(transaction -> {
            addItems(transaction, "ABC");
            addItems(transaction, "DEF");
            addItems(transaction, "GHI");

            // Simulating a condition that might cause a rollback
            if (database.size() > 5) {
                transaction.rollbackChanges();
            } else {
                transaction.commitChange();
            }
        });

        // Print the final state of the database
        System.out.println("Final database state: " + database);
    }

    static void addItems(Transaction transaction, String name) {
        System.out.println("Adding - " + name);
        database.add(name);

        // If rollback has to happen, then the following lambda will be executed from change list in Transaction
        // this lambda is stored in the changes list in transaction object.
        transaction.addChange(() -> database.remove(name));
    }

    static class TransactionManager {
        void manageTransaction(TransactionConsumer consumer) {
            // this is where the transaction object is created which is required for runLambda method
            Transaction transaction = new Transaction();
            try {
                // this is where the body of lambda executed in manageTransaction method.
                // And the transaction object is available in the lambda body
                consumer.runLambda(transaction);
            } catch (Exception ex) {
                transaction.rollbackChanges();
            }
        }
    }

    static class Transaction {
        // A transaction can have 3 operation.
        // 1. Add change
        // 2. Rollback the change
        // 3. Commit change

        // Any changes done in the txn is stored here before committing to database.
        // the Rollback is a functional interface to store the lambdas in the changes object.
        List<Rollback> rollbackAction = new ArrayList<>();

        /**
         * This method adds the action to be preformed to undo the changes done to the database.
         * @param action
         */
        void addChange(Rollback action) {
            rollbackAction.add(action);
        }

        void rollbackChanges() {
            // this method rollback all the changes that are done to database
            System.out.println("Rolling back the changes");
            rollbackAction.forEach(Rollback::doARollback); // This invokes the lambda stored in changes list
            rollbackAction.clear();
        }

        void commitChange() {
            System.out.println("Committing the changes");
            rollbackAction.clear(); //clean up the history after saving to database
        }
    }

    @FunctionalInterface
    interface TransactionConsumer {
        void runLambda(Transaction transaction); // the method takes a transaction object
    }

    @FunctionalInterface
    interface Rollback {
        void doARollback();
    }
}
