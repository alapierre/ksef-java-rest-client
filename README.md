# KSeF java REST client

KSeF
- API version: 1.0.0
  - Build date: 2022-01-02

Krajowy Systemu e-Faktur

This is very early stage of project. Code is not tested. Incompatible, braking changes are possible in the future.

Project goal is to create elastic KSeF API client with popular Java Http clients libraries and Json parsers.

Contributors are more than welcome!

## Sample usage

````java
import io.alapierre.ksef.client.*;
import io.alapierre.ksef.client.api.InterfejsyInteraktywneSesjaApi;
import io.alapierre.ksef.client.model.rest.auth.AuthorisationChallengeRequest;
import io.alapierre.ksef.client.okhttp.OkHttpApiClient;
import io.alapierre.ksef.client.serializer.gson.GsonJsonSerializer;
import lombok.val;

public class Main {

  public static void main(String[] args) throws ApiException {

    JsonSerializer serializer = new GsonJsonSerializer();
    ApiClient client = new OkHttpApiClient(serializer);

    InterfejsyInteraktywneSesjaApi sesjaApi = new InterfejsyInteraktywneSesjaApi(client);

    val challenge = sesjaApi.authorisationChallengeCall("NIP firmy", AuthorisationChallengeRequest.IdentifierType.onip);

    System.out.println(challenge);
  }

}
````

## Requirements

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



