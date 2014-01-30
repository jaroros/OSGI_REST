package osgi;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.http.HttpMessage;
import org.springframework.stereotype.Component;
import osgi.util.HTMLBody;

import javax.servlet.ServletOutputStream;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * User: Alexander Nazarenko
 */
@Component
public class PostProcess implements Processor {
    @Override
    public void process(final Exchange exchange) throws Exception {
        final HTMLBody body =  exchange.getIn().getBody(HTMLBody.class);
        final StringBuilder stringBuilderWriter = new StringBuilder();
        stringBuilderWriter.append(body.getHeader() + "</head>");
        stringBuilderWriter.append(body.getBody());
        stringBuilderWriter.append(body.getFooter() +
                "\n</body>\n" +
                "</html>");
        exchange.getIn().setBody(stringBuilderWriter.toString(), String.class);
    }
}
