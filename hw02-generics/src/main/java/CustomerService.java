import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

public class CustomerService {

    NavigableMap<Customer, String> customers = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Customer customer = customers.firstEntry().getKey();
        return new AbstractMap.SimpleEntry<>(new Customer(customer.getId(), customer.getName(), customer.getScores()),
                customers.firstEntry().getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> nextEntry = customers.higherEntry(customer);
        if (nextEntry == null)
            return null;
        Customer nextCustomer = nextEntry.getKey();
        return new AbstractMap.SimpleEntry<>(new Customer(nextCustomer.getId(), nextCustomer.getName(), nextCustomer.getScores()),
                nextEntry.getValue());
    }

    public void add(Customer customer, String data) {
        customers.put(customer, data);
    }
}

