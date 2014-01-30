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
public class CheckInFeaturesHandler extends SpringRouteBuilder {
    private final Set<String> listOfFeatures = Collections.synchronizedSet(new LinkedHashSet<String>());
    public final static String featuresHandler = "featuresHandler";
    @Override
    public void configure() throws Exception {
        from("activemq:" + featuresHandler).process(new Processor() {
            @Override
            public void process(final Exchange exchange) throws Exception {
                listOfFeatures.add(exchange.getIn().getBody(String.class));
            }
        });
    }
}
