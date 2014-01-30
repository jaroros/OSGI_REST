package osgi;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMessage;
import org.springframework.stereotype.Component;

/**
 * User: Alexander Nazarenko
 */
@Component
public class FeaturePreProcess implements Processor {
    @Override
    public void process(final Exchange exchange) throws Exception {
       final HttpMessage httpMessage =  exchange.getIn(HttpMessage.class);
       final String[] query =  httpMessage.getRequest().getRequestURI().split("/");
       final String feature =  query[query.length -1 ];
       if(feature.endsWith(".js")){
           exchange.getIn().setBody(feature.substring(0, feature.length() - 3), String.class);
       }
    }
}
