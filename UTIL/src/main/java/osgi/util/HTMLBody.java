package osgi.util;

import org.apache.camel.component.http.HttpMessage;

/**
 * User: Alexander Nazarenko
 */
public class HTMLBody {
    private HttpMessage message = null;
    private String body = null;
    private String header = null;
    private String customer = null;

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    private String footer = null;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public HttpMessage getMessage() {
        return message;
    }

    public void setMessage(HttpMessage message) {
        this.message = message;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
