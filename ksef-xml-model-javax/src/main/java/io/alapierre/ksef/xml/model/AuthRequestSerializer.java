package io.alapierre.ksef.xml.model;

import pl.com.softproject.utils.xml.BaseXMLSerializer;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.InitSessionSignedRequest;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.23
 */
public class AuthRequestSerializer extends BaseXMLSerializer<InitSessionSignedRequest> {

    public AuthRequestSerializer() {
        super("pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001", "authRequest.xsd", "https://ksef.mf.gov.pl/schema/gtw/svc/online/auth/request/2021/10/01/0001 https://ksef.mf.gov.pl/schema/gtw/svc/online/auth/request/2021/10/01/0001/authRequest.xsd");
    }

}
