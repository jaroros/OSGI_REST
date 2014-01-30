package osgi;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMessage;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import osgi.util.HTMLBody;

/**
 * User: Alexander Nazarenko
 */
@Component
public class PreProcess implements Processor {
    @Override
    public void process(final Exchange exchange) throws Exception {
        final HTMLBody htmlBody = new HTMLBody();
        htmlBody.setMessage((HttpMessage) exchange.getIn());

        final String customer = htmlBody.getMessage().getRequest().getParameter("customer");
        htmlBody.setCustomer(customer);
        exchange.getIn().setHeader("customer", customer);
        htmlBody.setHeader(IOUtils.toString(PreProcess.class.getClassLoader().getResourceAsStream("templates/header.ftl")));
        htmlBody.setFooter(IOUtils.toString(PreProcess.class.getClassLoader().getResourceAsStream("templates/footer.ftl")));
        exchange.getIn().setBody(htmlBody, HTMLBody.class);
    }
}
