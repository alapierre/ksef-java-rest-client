package io.alapierre.ksef.xml.model;

import lombok.var;
import org.jetbrains.annotations.NotNull;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.InitSessionSignedRequest;
import pl.gov.mf.ksef.schema.gtw.svc.online.auth.request._2021._10._01._0001.ObjectFactory;
import pl.gov.mf.ksef.schema.gtw.svc.types._2021._10._01._0001.*;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * @author Adrian Lapierre {@literal al@alapierre.io}
 * Copyrights by original author 2021.12.23
 */
public class AuthRequestUtil {

    public static @NotNull InitSessionSignedRequest prepareAuthRequest(@NotNull String challenge, @NotNull String identifier) {

        var factory = new ObjectFactory();
        var contextFactory = new pl.gov.mf.ksef.schema.gtw.svc.online.types._2021._10._01._0001.ObjectFactory();

        InitSessionSignedRequest request = factory.createInitSessionSignedRequest();

        var context = contextFactory.createAuthorisationContextSignedType();
        context.setType(AuthorisationTypeType.SERIAL_NUMBER);
        context.setChallenge(challenge);

        var documentTypeType = new DocumentTypeType();
        documentTypeType.setService(ServiceType.K_SE_F);

        var form = new FormCodeType();
        form.setSystemCode("FA (1)");
        form.setSchemaVersion("1-0E");
        form.setTargetNamespace("http://crd.gov.pl/wzor/2021/11/29/11089/");
        form.setValue("FA");

        documentTypeType.setFormCode(form);

        context.setDocumentType(documentTypeType);

        var id = new SubjectIdentifierByCompanyType();
        id.setIdentifier(identifier);
        context.setIdentifier(id);

        request.setContext(context);

        return request;
    }

    public static void requestToFile(@NotNull InitSessionSignedRequest request, @NotNull File outputFile) {
        AuthRequestSerializer serializer = new AuthRequestSerializer();
        serializer.toFile(request, outputFile.getAbsolutePath());
    }

    public static byte[] requestToBytes(@NotNull InitSessionSignedRequest request) {
        AuthRequestSerializer serializer = new AuthRequestSerializer();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        serializer.toStream(request, os);
        return os.toByteArray();
    }

}
