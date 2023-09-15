package io.alapierre.ksef.xml.model;

import lombok.val;
import org.junit.jupiter.api.Test;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.InitSessionTokenRequest;
import pl.gov.mf.ksef.schema.gtw.svc.online.types._2021._10._01._0001.AuthorisationContextTokenType;
import pl.gov.mf.ksef.schema.gtw.svc.types._2021._10._01._0001.DocumentTypeType;
import pl.gov.mf.ksef.schema.gtw.svc.types._2021._10._01._0001.FormCodeType;
import pl.gov.mf.ksef.schema.gtw.svc.types._2021._10._01._0001.ServiceType;
import pl.gov.mf.ksef.schema.gtw.svc.types._2021._10._01._0001.SubjectIdentifierByCompanyType;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2023.09.10
 */
public class SerializerTest {

    @Test
    void serializeWithUtil() {

        val serializer = new AuthTokenRequestSerializer();
        val request = AuthRequestUtil.prepareTokenAuthRequest("challenge", "identifier", "encryptedToken");

        serializer.toStream(request, System.out);

    }

    @Test
    void serialize() {

        val serializer = new AuthTokenRequestSerializer();

        val initSessionTokenRequest = new InitSessionTokenRequest();

        val subjectIdentifier = new SubjectIdentifierByCompanyType();
        subjectIdentifier.setIdentifier("identifier");

        val context = new AuthorisationContextTokenType();
        context.setChallenge("challenge");
        context.setIdentifier(subjectIdentifier);
        context.setToken("encryptedToken");

        val documentTypeType = new DocumentTypeType();
        documentTypeType.setService(ServiceType.K_SE_F);

        val form = new FormCodeType();
        form.setSystemCode("FA (1)");
        form.setSchemaVersion("1-0E");
        form.setTargetNamespace("http://crd.gov.pl/wzor/2021/11/29/11089/");
        form.setValue("FA");

        documentTypeType.setFormCode(form);

        context.setDocumentType(documentTypeType);
        initSessionTokenRequest.setContext(context);

        serializer.toStream(initSessionTokenRequest, System.out);

    }

}
