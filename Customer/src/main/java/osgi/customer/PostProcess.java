package osgi.customer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * User: Alexander Nazarenko
 */
@Component
public class PostProcess implements Processor {
    @Override
    public void process(final Exchange exchange) throws Exception {

    }
}
