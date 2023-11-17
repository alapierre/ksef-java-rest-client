package io.alapierre.ksef.token.facade;

import lombok.Getter;

@SuppressWarnings("unused")
public class SchemaConfiguration {

    @Getter
    private static boolean validateAuthRequestXML = true;

    public static void setValidateAuthRequestXML(boolean myVariable) {
        SchemaConfiguration.validateAuthRequestXML = myVariable;
    }
}
