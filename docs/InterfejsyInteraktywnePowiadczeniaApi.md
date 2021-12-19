# InterfejsyInteraktywnePowiadczeniaApi

All URIs are relative to *https://ksef-test.mf.gov.pl/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**generateToken**](InterfejsyInteraktywnePowiadczeniaApi.md#generateToken) | **POST** /online/Credentials/GenerateToken | Generowanie tokena autoryzacyjnego
[**grantCredentials**](InterfejsyInteraktywnePowiadczeniaApi.md#grantCredentials) | **POST** /online/Credentials/Grant | Nadanie poświadczeń
[**revokeCredentials**](InterfejsyInteraktywnePowiadczeniaApi.md#revokeCredentials) | **POST** /online/Credentials/Revoke | Odebranie poświadczeń
[**statusCredentials**](InterfejsyInteraktywnePowiadczeniaApi.md#statusCredentials) | **GET** /online/Credentials/Status/{CredentialsElementReferenceNumber} | Sprawdzenie statusu poświadczeń

<a name="generateToken"></a>
# **generateToken**
> GenerateTokenResponse generateToken(body)

Generowanie tokena autoryzacyjnego

Generowanie tokena autoryzacyjnego

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywnePowiadczeniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywnePowiadczeniaApi apiInstance = new InterfejsyInteraktywnePowiadczeniaApi();
GenerateTokenRequest body = new GenerateTokenRequest(); // GenerateTokenRequest | 
try {
    GenerateTokenResponse result = apiInstance.generateToken(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywnePowiadczeniaApi#generateToken");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**GenerateTokenRequest**](GenerateTokenRequest.md)|  |

### Return type

[**GenerateTokenResponse**](GenerateTokenResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="grantCredentials"></a>
# **grantCredentials**
> StatusCredentialsResponse grantCredentials(body)

Nadanie poświadczeń

Nadanie poświadczeń

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywnePowiadczeniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywnePowiadczeniaApi apiInstance = new InterfejsyInteraktywnePowiadczeniaApi();
GrantCredentialsRequest body = new GrantCredentialsRequest(); // GrantCredentialsRequest | 
try {
    StatusCredentialsResponse result = apiInstance.grantCredentials(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywnePowiadczeniaApi#grantCredentials");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**GrantCredentialsRequest**](GrantCredentialsRequest.md)|  |

### Return type

[**StatusCredentialsResponse**](StatusCredentialsResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="revokeCredentials"></a>
# **revokeCredentials**
> StatusCredentialsResponse revokeCredentials(body)

Odebranie poświadczeń

Odebranie poświadczeń

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywnePowiadczeniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywnePowiadczeniaApi apiInstance = new InterfejsyInteraktywnePowiadczeniaApi();
RevokeCredentialsRequest body = new RevokeCredentialsRequest(); // RevokeCredentialsRequest | 
try {
    StatusCredentialsResponse result = apiInstance.revokeCredentials(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywnePowiadczeniaApi#revokeCredentials");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RevokeCredentialsRequest**](RevokeCredentialsRequest.md)|  |

### Return type

[**StatusCredentialsResponse**](StatusCredentialsResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="statusCredentials"></a>
# **statusCredentials**
> StatusCredentialsResponse statusCredentials(credentialsElementReferenceNumber)

Sprawdzenie statusu poświadczeń

Sprawdzenie statusu poświadczeń

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywnePowiadczeniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywnePowiadczeniaApi apiInstance = new InterfejsyInteraktywnePowiadczeniaApi();
String credentialsElementReferenceNumber = "credentialsElementReferenceNumber_example"; // String | 
try {
    StatusCredentialsResponse result = apiInstance.statusCredentials(credentialsElementReferenceNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywnePowiadczeniaApi#statusCredentials");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **credentialsElementReferenceNumber** | **String**|  |

### Return type

[**StatusCredentialsResponse**](StatusCredentialsResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

