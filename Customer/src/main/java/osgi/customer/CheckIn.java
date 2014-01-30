package osgi.customer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

/**
 * User: Alexander Nazarenko
 */
@Component
public class CheckIn extends SpringRouteBuilder {
    public final static String timerName = "CheckInTitan";
    public final static String periodTimer = "60000";

    @Override
    public void configure() throws Exception {
        from("timer://" + timerName + "?fixedRate=true&period=" + periodTimer).process(new Processor() {
            @Override
            public void process(final Exchange exchange) throws Exception {
                exchange.getIn().setBody(MainRouteTitan.featureName);
            }
        }).to("activemq:customersHandler");
    }
}
