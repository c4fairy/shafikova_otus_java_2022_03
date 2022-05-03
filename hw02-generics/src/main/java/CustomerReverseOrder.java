import java.util.ArrayDeque;
import java.util.Deque;

public class CustomerReverseOrder {

    private Deque<Customer> customers = new ArrayDeque<>();

    public void add(Customer customer) {
        customers.add(customer);
    }

    //Retrieves and removes the last element of this deque, or returns null if this deque is empty.
    public Customer take() {
        return customers.pollLast();
    }
}