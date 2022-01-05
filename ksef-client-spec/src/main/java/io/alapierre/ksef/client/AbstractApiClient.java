package io.alapierre.ksef.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.NotNull;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2022.01.02
 */
public abstract class AbstractApiClient implements ApiClient {

    private final String url;

    protected AbstractApiClient() {
        url = Environment.TEST.getUrl();
    }

    protected AbstractApiClient(String url) {
        this.url = url;
    }

    protected AbstractApiClient(Environment environment) {
        url = environment.getUrl();
    }

    protected byte[] marshalXML(@NotNull Object o) throws JAXBException {

        val jc = JAXBContext.newInstance(o.getClass());
        val jaxbMarshaller = jc.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        val stream = new ByteArrayOutputStream();
        jaxbMarshaller.marshal(o, stream);

        return stream.toByteArray();
    }

    @NotNull
    protected String createUrl(@NotNull String endpoint) {

        val sb = new StringBuilder();
        sb.append(url);
        if (!endpoint.startsWith("/")) sb.append("/");
        sb.append(endpoint);

        return sb.toString();
    }

    @Getter
    @RequiredArgsConstructor
    public enum Environment {

        DEMO("https://ksef-demo.mf.gov.pl/api"),
        PROD("https://ksef.mf.gov.pl/api"),
        TEST("https://ksef-test.mf.gov.pl/api");

        private final String url;
    }
}
