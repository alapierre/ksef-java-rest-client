[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=alapierre_ksef-java-rest-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=alapierre_ksef-java-rest-client)
[![Renovate enabled](https://img.shields.io/badge/renovate-enabled-brightgreen.svg)](https://renovatebot.com/)
[![Maven Central](http://img.shields.io/maven-central/v/io.github.fvarrui/javapackager)](https://search.maven.org/artifact/io.github.fvarrui/javapackager)

# KSeF java REST client

KSeF
- API version: 1.0.0
  - Build date: 2022-01-15

Krajowy Systemu e-Faktur

Projekt na bardzo wczesnym etapie rozwoju. Status pre-alfa. Przedstawione poniżej przykłady mogą nie działać prawidłowo. 

Celem projektu jest stworzenie elastycznego klienta API KSeF na platformę Java, z wykorzystaniem 
popularnych bibliotek wywołań http i serializacji JSON.  Obecnie zaimplementowany jest tylko klient OkHttp `OkHttpApiClient` i serializer Gson `GsonJsonSerializer`.

Pomoc w rozwoju projektu jest bardzo mile widziana. 

## Przykładowe żądania

### Zależności projektowe

````xml
<dependencies>
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
    
      <dependency>
        <groupId>io.alapierre.crypto</groupId>
        <artifactId>digital-signature</artifactId>
        <version>1.0</version>
      </dependency>
    
      <dependency>
        <groupId>io.alapierre.ksef</groupId>
        <artifactId>ksef-xml-model</artifactId>
        <version>${project.version}</version>
      </dependency>
    
</dependencies>
````

### Pobranie wyzwania autoryzacyjnego, autoryzacja podpisem i wysłanie faktury

````java
import eu.europa.esig.dss.model.DSSDocument;
import io.alapierre.crypto.dss.signer.P12Signer;
import io.alapierre.ksef.client.ApiClient;
import io.alapierre.ksef.client.ApiException;
import io.alapierre.ksef.client.JsonSerializer;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneFakturaApi;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import io.alapierre.ksef.xml.model.AuthRequestUtil;
import lombok.val;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.KeyStore;

public class Main {

  private final static File tokenFile = new File("token.p12");
  private final static KeyStore.PasswordProtection pas = new KeyStore.PasswordProtection("_____token_password_____".toCharArray());
  
  public static void main(String[] args)  {

    try {

      JsonSerializer serializer = new GsonJsonSerializer();
      ApiClient client = new OkHttpApiClient(serializer);

      InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

      val identifier = "NIP firmy";
      val challenge = sesjaApi.authorisationChallengeCall(identifier, AuthorisationChallengeRequest.IdentifierType.onip);

      System.out.println(challenge);

      val auth = AuthRequestUtil.prepareAuthRequest(challenge.getChallenge(), identifier);
      val toSigned = AuthRequestUtil.requestToBytes(auth);

      // podpis elektroniczny XML 
      ByteArrayOutputStream signed = signRequest(toSigned);

      val signedResponse = sesjaApi.initSessionSignedCall(challenge.getChallenge(), identifier, signed.toByteArray());
      // signedResponse.getSessionToken() zawiera token sesyjny, który jest niezbędny do kolejnych wywołań API

      val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);
      invoiceApi.invoiceSend(new File("FA1.xml"), signedResponse.getSessionToken().getToken());

    } catch (ApiException ex) {
      System.out.printf("Błąd wywołania API %d (%s) opis błędu %s", ex.getCode(), ex.getMessage(),  ex.getResponseBody());
    } catch (IOException e) {
      System.out.println("Błąd operacji IO: " + e.getMessage());
    }
  }

  public static ByteArrayOutputStream signRequest(byte[] toSigned) throws IOException {

    val signer = new P12Signer(pas, tokenFile);

    ByteArrayInputStream is = new ByteArrayInputStream(toSigned);
    DSSDocument signedDocument = signer.sign(is);

    ByteArrayOutputStream signed = new ByteArrayOutputStream();
    signedDocument.writeTo(signed);

    return signed;
  }
}
````

Biblioteka ułatwiająca wykonanie podpisu elektronicznego XADES na karcie kryptograficznej lub za pomocą tokena w formacie PKCS#12: 

````xml
<dependency>
  <groupId>io.alapierre.crypto</groupId>
  <artifactId>digital-signature</artifactId>
  <version>1.0</version>
</dependency>
````

## Build Requirements

Building the API client library requires:
1. Java 1.8+ and Java 11 (to build `ksef-client-jdk11-http-client`)
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
