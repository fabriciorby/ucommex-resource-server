package br.com.ucommex.resourceserver.feedback.helper;

import br.com.ucommex.resourceserver.feedback.pojo.CustomerFeedback;
import br.com.ucommex.resourceserver.feedback.pojo.EmployeeFeedback;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.IndexOptions;
import org.bson.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import static br.com.ucommex.resourceserver.db.MongoConnect.getDatabase;

public class FeedbackDbHelper {

    private static MongoCollection<CustomerFeedback> getCustomerFeedbackCollection() {
        return getDatabase().getCollection("feedbackCliente", CustomerFeedback.class);
    }

    private static MongoCollection<EmployeeFeedback> getEmployeeFeedbackCollection() {
        return getDatabase().getCollection("feedbackLojista", EmployeeFeedback.class);
    }

    public static void insert(CustomerFeedback customerFeedback) {
        MongoCollection<CustomerFeedback> collection = getCustomerFeedbackCollection();
//        collection.createIndex(new Document("cpf", 1), new IndexOptions().unique(true));
        collection.insertOne(customerFeedback);
    }

    public static void insert(EmployeeFeedback employeeFeedback) {
        MongoCollection<EmployeeFeedback> collection = getEmployeeFeedbackCollection();
//        collection.createIndex(new Document("cpfCliente", 1).append("idLojista", 1),
//                new IndexOptions().unique(true));
        collection.insertOne(employeeFeedback);
    }

    public static Set<CustomerFeedback> getCustomerFeedbacks() {
        Set<CustomerFeedback> customerFeedbacks = new HashSet();
        getCustomerFeedbackCollection().find()
                .forEach((Consumer<CustomerFeedback>) customerFeedbacks::add);
        return customerFeedbacks;
    }

    public static Set<EmployeeFeedback> getEmployeeFeedbacks() {
        Set<EmployeeFeedback> employeeFeedbacks = new HashSet();
        getEmployeeFeedbackCollection().find()
                .forEach((Consumer<EmployeeFeedback>) employeeFeedbacks::add);
        return employeeFeedbacks;
    }
}
