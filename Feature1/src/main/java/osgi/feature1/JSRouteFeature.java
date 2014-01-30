package osgi.feature1;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

/**
 * User: Alexander Nazarenko
 */
@Component
public class JSRouteFeature extends SpringRouteBuilder {
    @Override
    public void configure() throws Exception {
        from("direct-vm:" + HTMLRouteFeature1.featureName + "JS").process(new Processor() {
            @Override
            public void process(final Exchange exchange) throws Exception {
                final String feature = IOUtils.toString(HTMLRouteFeature1.class.getClassLoader().getResourceAsStream("feature.js"));
                exchange.getIn().setBody(feature);
            }
        });
    }
}
