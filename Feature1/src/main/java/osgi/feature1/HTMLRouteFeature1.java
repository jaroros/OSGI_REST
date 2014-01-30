package osgi.feature1;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spring.SpringRouteBuilder;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import osgi.util.HTMLBody;

/**
 * User: Alexander Nazarenko
 */
@Component
public class HTMLRouteFeature1 extends SpringRouteBuilder {
    public static final String featureName = "feature1";

    @Override
    public void configure() throws Exception {
        from("direct-vm:" + featureName + "HTML").process(new Processor() {
            @Override
            public void process(final Exchange exchange) throws Exception {
                final HTMLBody htmlBody = (HTMLBody) exchange.getIn().getBody();
                final String feature = IOUtils.toString(HTMLRouteFeature1.class.getClassLoader().getResourceAsStream("feature.js"));
                htmlBody.setHeader(htmlBody.getHeader().concat("\n" + featureMetaData("feature1.js")));
            }
        });
    }

    public static String featureMetaData(final String fileName) {
        return "<script type=\"text/javascript\"  src=\"features/" + fileName + "\"></script>";

    }
}
