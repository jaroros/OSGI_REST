package osgi.customer;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import osgi.util.HTMLBody;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Alexander Nazarenko
 */
@Component
public class MainRouteTitan extends SpringRouteBuilder {
    @Autowired
    private PreProcess preProcess;
    @Autowired
    private PostProcess postProcess;
    public final static String featureName  = "titanBet";
    @Value("${titanbet.features}")
    private String features;

    @Override
    public void configure() throws Exception {
        final String[]  featuresArray = features.split(",");
        final List<String> featuresList = new ArrayList<String>();
        for(final String feature :featuresArray){
            featuresList.add("direct-vm:" + feature +  "HTML");
        }
        from("direct-vm:" + featureName).process(preProcess).process(new Processor() {
            @Override
            public void process(final Exchange exchange) throws Exception {
                final HTMLBody htmlBody = (HTMLBody) exchange.getIn().getBody();
                final String customerBody = IOUtils.toString(MainRouteTitan.class.getClassLoader().getResourceAsStream("index.ftl"));
                htmlBody.setBody(customerBody);
            }
        }) .multicast().to(featuresList.toArray(new String[0]))
                .process(postProcess);
    }
}
