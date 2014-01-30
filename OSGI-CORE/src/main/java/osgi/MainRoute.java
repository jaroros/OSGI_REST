package osgi;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: Alexander Nazarenko
 */
@Component
public class MainRoute extends RouteBuilder {
    @Autowired
    private PreProcess preProcess;
    @Autowired
    private PostProcess postProcess;

    @Override
    public void configure() throws Exception {
        from("jetty:http://localhost:9090/myapp/myservice")
                .process(preProcess).recipientList().simple("direct-vm:${body.customer}")
                .process(postProcess);
    }
}
