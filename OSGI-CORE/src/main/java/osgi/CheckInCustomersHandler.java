package osgi;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: Alexander Nazarenko
 */
@Component
public class CheckInCustomersHandler extends SpringRouteBuilder {
    private final Set<String> listOfCustomers = Collections.synchronizedSet(new LinkedHashSet<String>());
    public final static String customersHandler = "customersHandler";
    @Override
    public void configure() throws Exception {
        from("activemq:" + customersHandler).process(new Processor() {
            @Override
            public void process(final Exchange exchange) throws Exception {
                listOfCustomers.add(exchange.getIn().getBody(String.class));
            }
        });
    }
}
