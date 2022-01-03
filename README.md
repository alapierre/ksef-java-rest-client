# KSeF java REST client

KSeF
- API version: 1.0.0
  - Build date: 2022-01-02

Krajowy Systemu e-Faktur

Projekt na bardzo wczesnym etapie rozwoju.

Celem projektu jest stworzenie elastycznego klienta API KSeF na platformę Java, z wykorzystaniem 
popularnych bibliotek wywołań http i serializacji JSON.  

Pomoc w rozwoju projektu jest mile widziana. 

## Przykładowe żądania

### Zależności projektowe

````xml
         <dependency>
            <groupId>io.alapierre.ksef</groupId>
            <artifactId>ksef-client-okhttp</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>io.alapierre.ksef</groupId>
            <artifactId>ksef-gson-serializer</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>io.alapierre.ksef</groupId>
            <artifactId>ksef-api</artifactId>
            <version>${project.version}</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
````

### Pobranie wyzwania autoryzacyjnego, autoryzacja podpisem i wysłanie faktury

````java
import io.alapierre.ksef.client.*;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.xml.model.AuthRequestUtil;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import lombok.val;

public class Main {

  public static void main(String[] args) throws ApiException {

    JsonSerializer serializer = new GsonJsonSerializer();
    ApiClient client = new OkHttpApiClient(serializer);
    
    val identifier = "NIP firmy";

    InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

    val challenge = sesjaApi.authorisationChallengeCall(identifier, AuthorisationChallengeRequest.IdentifierType.onip);

    System.out.println(challenge);

    val auth = AuthRequestUtil.prepareAuthRequest(challenge.getChallenge(), identifier);
    val toSigned = AuthRequestUtil.requestToBytes(auth);

    // podpis elektroniczny toSigned i zapis podisanego XML do ByteArrayOutputStream signed 

    val signedResponse = api.initSessionSignedCall(challenge.getChallenge(), identifier, signed.toByteArray());

    // signedResponse.getSessionToken() zawiera token sesyjny

    val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);

    invoiceApi.invoiceSend(new File("FA1.xml"), signedResponse.getSessionToken());

    
  }

}
````

Podpis elektroniczny

````xml
<dependency>
  <groupId>io.alapierre.crypto</groupId>
  <artifactId>crypto-util</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
````

````java
import io.alapierre.crypto.dss.signer.P12Signer;
import eu.europa.esig.dss.model.DSSDocument;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

val signer = new P12Signer(pass, token);

ByteArrayInputStream is = new ByteArrayInputStream(toSigned);
DSSDocument signedDocument = signer.sign(is);

ByteArrayOutputStream signed = new ByteArrayOutputStream();
signedDocument.writeTo(signed);
````

## Build Requirements

Building the API client library requires:
1. Java 1.8+ and Java 11 (tu build `ksef-client-jdk11-http-client`)
2. Maven
3. Maven configured to build multi JDK projects

## Configuring maven to build multi JDK projects

put toolchain.xml file in your `${user.home}/.m2`:

````xml
<?xml version="1.0" encoding="UTF-8"?>
<toolchains>
    <!-- JDK toolchains -->
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>1.8</version>
            <vendor>openjdk</vendor>
        </provides>
        <configuration>
            <jdkHome>path to jdk 1.8</jdkHome>
        </configuration>
    </toolchain>
    <toolchain>
        <type>jdk</type>
        <provides>
            <version>11</version>
            <vendor>openjdk</vendor>
        </provides>
        <configuration>
            <jdkHome>path to jdk 11</jdkHome>
        </configuration>
    </toolchain>
</toolchains>

````



