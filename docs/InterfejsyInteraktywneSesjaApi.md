# InterfejsyInteraktywneSesjaApi

All URIs are relative to *https://ksef-test.mf.gov.pl/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**authorisationChallenge**](InterfejsyInteraktywneSesjaApi.md#authorisationChallenge) | **POST** /online/Session/AuthorisationChallenge | Inicjalizacja mechanizmu uwierzytelnienia i autoryzacji
[**initSessionSigned**](InterfejsyInteraktywneSesjaApi.md#initSessionSigned) | **POST** /online/Session/InitSigned | Inicjalizacja sesji, wskazanie kontekstu, uwierzytelnienie i autoryzacja
[**initSessionToken**](InterfejsyInteraktywneSesjaApi.md#initSessionToken) | **POST** /online/Session/InitToken | Inicjalizacja sesji, wskazanie kontekstu, uwierzytelnienie i autoryzacja
[**sessionStatusPlain**](InterfejsyInteraktywneSesjaApi.md#sessionStatusPlain) | **GET** /online/Session/Status | Sprawdzenie statusu aktywnej sesji interaktywnej
[**sessionStatusReferenceNumber**](InterfejsyInteraktywneSesjaApi.md#sessionStatusReferenceNumber) | **GET** /online/Session/Status/{ReferenceNumber} | Sprawdzenie statusu sesji ogólnej
[**terminateSession**](InterfejsyInteraktywneSesjaApi.md#terminateSession) | **GET** /online/Session/Terminate | Wymuszenie zamknięcia sesji

<a name="authorisationChallenge"></a>
# **authorisationChallenge**
> AuthorisationChallengeResponse authorisationChallenge(body)

Inicjalizacja mechanizmu uwierzytelnienia i autoryzacji

Inicjalizacja mechanizmu uwierzytelnienia i autoryzacji.

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InterfejsyInteraktywneSesjaApi;


InterfejsyInteraktywneSesjaApi apiInstance = new InterfejsyInteraktywneSesjaApi();
AuthorisationChallengeRequest body = new AuthorisationChallengeRequest(); // AuthorisationChallengeRequest | 
try {
    AuthorisationChallengeResponse result = apiInstance.authorisationChallenge(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneSesjaApi#authorisationChallenge");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**AuthorisationChallengeRequest**](AuthorisationChallengeRequest.md)|  |

### Return type

[**AuthorisationChallengeResponse**](AuthorisationChallengeResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="initSessionSigned"></a>
# **initSessionSigned**
> InitSessionResponse initSessionSigned(body)

Inicjalizacja sesji, wskazanie kontekstu, uwierzytelnienie i autoryzacja

Inicjalizacja sesji interaktywnej. Podpisany dokument http://ksef.mf.gov.pl/schema/gtw/svc/online/auth/request/2021/10/01/0001/InitSessionSignedRequest

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InterfejsyInteraktywneSesjaApi;


InterfejsyInteraktywneSesjaApi apiInstance = new InterfejsyInteraktywneSesjaApi();
Object body = null; // Object | 
try {
    InitSessionResponse result = apiInstance.initSessionSigned(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneSesjaApi#initSessionSigned");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | **Object**|  |

### Return type

[**InitSessionResponse**](InitSessionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/octet-stream
 - **Accept**: application/json

<a name="initSessionToken"></a>
# **initSessionToken**
> InitSessionResponse initSessionToken(body)

Inicjalizacja sesji, wskazanie kontekstu, uwierzytelnienie i autoryzacja

Inicjalizacja sesji interaktywnej. Zaszyfrowany kluczem publicznym KSeF dokument http://ksef.mf.gov.pl/schema/gtw/svc/online/auth/request/2021/10/01/0001/InitSessionTokenRequest

### Example
```java
// Import classes:
//import io.swagger.client.ApiException;
//import io.swagger.client.api.InterfejsyInteraktywneSesjaApi;


InterfejsyInteraktywneSesjaApi apiInstance = new InterfejsyInteraktywneSesjaApi();
Object body = null; // Object | 
try {
    InitSessionResponse result = apiInstance.initSessionToken(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneSesjaApi#initSessionToken");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | **Object**|  |

### Return type

[**InitSessionResponse**](InitSessionResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/octet-stream
 - **Accept**: application/json

<a name="sessionStatusPlain"></a>
# **sessionStatusPlain**
> SessionStatusResponse sessionStatusPlain(pageSize, pageOffset)

Sprawdzenie statusu aktywnej sesji interaktywnej

Sprawdzenie statusu obecnego przetwarzania interaktywnego

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneSesjaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneSesjaApi apiInstance = new InterfejsyInteraktywneSesjaApi();
Integer pageSize = 56; // Integer | 
Integer pageOffset = 56; // Integer | 
try {
    SessionStatusResponse result = apiInstance.sessionStatusPlain(pageSize, pageOffset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneSesjaApi#sessionStatusPlain");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pageSize** | **Integer**|  | [enum: ]
 **pageOffset** | **Integer**|  | [enum: ]

### Return type

[**SessionStatusResponse**](SessionStatusResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="sessionStatusReferenceNumber"></a>
# **sessionStatusReferenceNumber**
> SessionStatusResponse sessionStatusReferenceNumber(referenceNumber, pageSize, pageOffset)

Sprawdzenie statusu sesji ogólnej

Sprawdzenie statusu przetwarzania na podstawie numeru referencyjnego

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneSesjaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneSesjaApi apiInstance = new InterfejsyInteraktywneSesjaApi();
String referenceNumber = "referenceNumber_example"; // String | 
Integer pageSize = 56; // Integer | 
Integer pageOffset = 56; // Integer | 
try {
    SessionStatusResponse result = apiInstance.sessionStatusReferenceNumber(referenceNumber, pageSize, pageOffset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneSesjaApi#sessionStatusReferenceNumber");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **referenceNumber** | **String**|  |
 **pageSize** | **Integer**|  | [enum: ]
 **pageOffset** | **Integer**|  | [enum: ]

### Return type

[**SessionStatusResponse**](SessionStatusResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="terminateSession"></a>
# **terminateSession**
> TerminateSessionResponse terminateSession()

Wymuszenie zamknięcia sesji

Wymuszenie zamknięcia sesji interaktywnej

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneSesjaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneSesjaApi apiInstance = new InterfejsyInteraktywneSesjaApi();
try {
    TerminateSessionResponse result = apiInstance.terminateSession();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneSesjaApi#terminateSession");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**TerminateSessionResponse**](TerminateSessionResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

