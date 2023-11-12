[![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=alapierre_ksef-java-rest-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=alapierre_ksef-java-rest-client)
[![Renovate enabled](https://img.shields.io/badge/renovate-enabled-brightgreen.svg)](https://renovatebot.com/)
[![Maven Central](http://img.shields.io/maven-central/v/io.alapierre.ksef/ksef-java)](https://search.maven.org/artifact/io.alapierre.ksef/ksef-java)

# KSeF java REST client

KSeF
- API version: 2.0.4
- Build date: 2023-11-12
- wspierana wersja schemy XML: FA (2)

Krajowy Systemu e-Faktur

Projekt sprawdzony produkcyjnie. Status: RC2.  

Celem projektu jest stworzenie elastycznego klienta API KSeF na platformę Java, z wykorzystaniem 
popularnych bibliotek wywołań http i serializacji JSON.  Obecnie zaimplementowany jest klient OkHttp `OkHttpApiClient` oraz JDK11+ `HttpClient` a także serializer Gson `GsonJsonSerializer` i Jackson.

Stabilne wersje są dostępne w Maven Central (nie ma potrzeby samodzielnego budowania ze źródeł): [![Maven Central](http://img.shields.io/maven-central/v/io.alapierre.ksef/ksef-java)](https://search.maven.org/artifact/io.alapierre.ksef/ksef-java)

Pomoc w rozwoju projektu jest bardzo mile widziana. 

## Przykładowe żądania

### Zależności projektowe

````xml
<dependencies>
    <dependency>
        <groupId>io.alapierre.ksef</groupId>
        <artifactId>ksef-client-okhttp</artifactId>
        <version>2.0.18</version>
    </dependency>

    <dependency>
        <groupId>io.alapierre.ksef</groupId>
        <artifactId>ksef-json-serializer-gson</artifactId>
        <version>2.0.18</version>
    </dependency>

    <dependency>
        <groupId>io.alapierre.ksef</groupId>
        <artifactId>ksef-token-facade</artifactId>
        <version>2.0.18</version>
    </dependency>

    <dependency>
        <groupId>io.alapierre.ksef</groupId>
        <artifactId>ksef-dss-facade</artifactId>
        <version>2.0.18</version>
        <scope>compile</scope>
    </dependency>
    
</dependencies>
````

### Autoryzacja tokenem i wysłanie faktury

````java

import io.alapierre.ksef.token.facade.*;

public class Main {

    public static final String NIP_FIRMY = "NIP firmy";

    public static void loginByToken() throws Exception {

        JsonSerializer serializer = new GsonJsonSerializer();
        ApiClient client = new OkHttpApiClient(serializer);

        InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

        val facade = new KsefTokenFacade(sesjaApi);
        InitSignedResponse session = facade.authByToken(NIP_FIRMY, Environment.TEST, AuthorisationChallengeRequest.IdentifierType.onip, "token");

        val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);
        invoiceApi.invoiceSend(new File("FA1.xml"), session.getSessionToken().getToken());
    }
    
}

````

### Autoryzacja podpisem i wysłanie faktury

````java
package io.alapierre.ksef.sample;

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

  public static final String NIP_FIRMY = "NIP firmy";
  private static final  File tokenFile = new File("token.p12");
  private static final  KeyStore.PasswordProtection pas = new KeyStore.PasswordProtection("_____token_password_____".toCharArray());

  public static void main(String[] args)  {

    try {

        JsonSerializer serializer = new GsonJsonSerializer();
        ApiClient client = new OkHttpApiClient(serializer);
        InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

        val signer = new P12Signer(pas, tokenFile);
        KsefDssFacade facade = new KsefDssFacade(signer, sesjaApi);

        val signedResponse = facade.authByDigitalSignature(NIP_FIRMY, IdentifierType.onip);

        // signedResponse.getSessionToken() zawiera token sesyjny

        val invoiceApi = new InterfejsyInteraktywneFakturaApi(client);
        invoiceApi.invoiceSend(new File("FA1.xml"), signedResponse.getSessionToken().getToken());

    } catch (ApiException ex) {
      System.out.printf("Błąd wywołania API %d (%s) opis błędu %s", ex.getCode(), ex.getMessage(),  ex.getResponseBody());
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
````

# Pobranie faktur zakupowych 

````java
public class Main {

    public static final String NIP_FIRMY = "NIP firmy";
    
    public static void loadIncomingInvoices() throws Exception {

        JsonSerializer serializer = new GsonJsonSerializer();
        ApiClient client = new OkHttpApiClient(serializer, Environment.TEST);

        InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

        val facade = new KsefTokenFacade(sesjaApi);
        InitSignedResponse session = facade.authByToken(Environment.TEST, NIP_FIRMY, IdentifierType.onip, "token");
        val sessionToken = session.getSessionToken().getToken();
        
        val zapytaniaApi = new InterfejsyInteraktywneZapytaniaApi(client);

        val request = InvoiceQueryRequest.builder()
                .queryCriteria(InvoiceQueryRequest.QueryCriteria.builder()
                        .subjectType("subject2")
                        .type("incremental")
                        .acquisitionTimestampThresholdFrom(zapytaniaApi.convertDate(DateUtils.firstDayOfMonth(LocalDate.now())))
                        .acquisitionTimestampThresholdTo(zapytaniaApi.convertDate(LocalDateTime.now()))
                        .build())
                .build();
        
        KsefResultStream.builder(
                        page -> new InvoiceQueryResponseAdapter(zapytaniaApi.invoiceQuery(sessionToken, request, 100, page)))
                .forEach(System.out::println);
    }
}
````

Biblioteka ułatwiająca wykonanie podpisu elektronicznego XADES na karcie kryptograficznej lub za pomocą tokena w formacie PKCS#12: 

````xml
<dependency>
  <groupId>io.alapierre.crypto</groupId>
  <artifactId>digital-signature</artifactId>
  <version>RELEASE</version>
</dependency>
````

## Build Requirements

The project can be built on JDK11+, and stating from version 2.0.13, we will stop supporting JDK8 at all. The last JDK8 compatible version was 2.0.12.

Building the API client library requires:
1. Java 11+
2. Maven
