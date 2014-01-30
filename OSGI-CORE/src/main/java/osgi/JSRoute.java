package osgi;

import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Alexander Nazarenko
 */
@Component
public class JSRoute extends SpringRouteBuilder {
    @Autowired
    private FeaturePreProcess featurePreProcess;
    @Override
    public void configure() throws Exception {
        from("jetty:http://localhost:9090/myapp/features?matchOnUriPrefix=true")
                .process(featurePreProcess).recipientList().simple("direct-vm:${body}JS");
    }
}
