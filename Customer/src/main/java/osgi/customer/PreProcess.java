package osgi.customer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * User: Alexander Nazarenko
 */
@Component
public class PreProcess implements Processor {
    @Override
    public void process(final Exchange exchange) throws Exception {
        System.out.println(exchange.getIn().getBody().toString());
    }

}
