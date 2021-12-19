# InterfejsyInteraktywneZapytaniaApi

All URIs are relative to *https://ksef-test.mf.gov.pl/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**queryCredentialsSync**](InterfejsyInteraktywneZapytaniaApi.md#queryCredentialsSync) | **POST** /online/Query/Credential/Sync | Zapytanie o poświadczenia
[**queryInvoiceAsyncFetch**](InterfejsyInteraktywneZapytaniaApi.md#queryInvoiceAsyncFetch) | **GET** /online/Query/Invoice/Async/Fetch/{QueryElementReferenceNumber}/{PartElementReferenceNumber} | Pobranie wyników zapytania o faktury
[**queryInvoiceAsyncInit**](InterfejsyInteraktywneZapytaniaApi.md#queryInvoiceAsyncInit) | **POST** /online/Query/Invoice/Async/Init | Inicjalizacja zapytania o faktury
[**queryInvoiceAsyncStatus**](InterfejsyInteraktywneZapytaniaApi.md#queryInvoiceAsyncStatus) | **GET** /online/Query/Invoice/Async/Status/{QueryElementReferenceNumber} | Sprawdzenie statusu zapytania o faktury
[**queryInvoiceSync**](InterfejsyInteraktywneZapytaniaApi.md#queryInvoiceSync) | **POST** /online/Query/Invoice/Sync | Zapytanie o faktury

<a name="queryCredentialsSync"></a>
# **queryCredentialsSync**
> QuerySyncCredentialsResponse queryCredentialsSync(body)

Zapytanie o poświadczenia

Zapytanie o poświadczenia

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneZapytaniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneZapytaniaApi apiInstance = new InterfejsyInteraktywneZapytaniaApi();
QuerySyncCredentialsRequest body = new QuerySyncCredentialsRequest(); // QuerySyncCredentialsRequest | 
try {
    QuerySyncCredentialsResponse result = apiInstance.queryCredentialsSync(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneZapytaniaApi#queryCredentialsSync");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**QuerySyncCredentialsRequest**](QuerySyncCredentialsRequest.md)|  |

### Return type

[**QuerySyncCredentialsResponse**](QuerySyncCredentialsResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryInvoiceAsyncFetch"></a>
# **queryInvoiceAsyncFetch**
> Object queryInvoiceAsyncFetch(queryElementReferenceNumber, partElementReferenceNumber)

Pobranie wyników zapytania o faktury

Pobranie wyników zapytania o faktury

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneZapytaniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneZapytaniaApi apiInstance = new InterfejsyInteraktywneZapytaniaApi();
String queryElementReferenceNumber = "queryElementReferenceNumber_example"; // String | 
String partElementReferenceNumber = "partElementReferenceNumber_example"; // String | 
try {
    Object result = apiInstance.queryInvoiceAsyncFetch(queryElementReferenceNumber, partElementReferenceNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneZapytaniaApi#queryInvoiceAsyncFetch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **queryElementReferenceNumber** | **String**|  |
 **partElementReferenceNumber** | **String**|  |

### Return type

**Object**

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream, application/json

<a name="queryInvoiceAsyncInit"></a>
# **queryInvoiceAsyncInit**
> QueryInvoiceAsyncInitResponse queryInvoiceAsyncInit(body)

Inicjalizacja zapytania o faktury

Inicjalizacja zapytania o faktury

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneZapytaniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneZapytaniaApi apiInstance = new InterfejsyInteraktywneZapytaniaApi();
QueryInvoiceRequest body = new QueryInvoiceRequest(); // QueryInvoiceRequest | 
try {
    QueryInvoiceAsyncInitResponse result = apiInstance.queryInvoiceAsyncInit(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneZapytaniaApi#queryInvoiceAsyncInit");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**QueryInvoiceRequest**](QueryInvoiceRequest.md)|  |

### Return type

[**QueryInvoiceAsyncInitResponse**](QueryInvoiceAsyncInitResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="queryInvoiceAsyncStatus"></a>
# **queryInvoiceAsyncStatus**
> QueryInvoiceAsyncStatusResponse queryInvoiceAsyncStatus(queryElementReferenceNumber)

Sprawdzenie statusu zapytania o faktury

Sprawdzenie statusu zapytania o faktury

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneZapytaniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneZapytaniaApi apiInstance = new InterfejsyInteraktywneZapytaniaApi();
String queryElementReferenceNumber = "queryElementReferenceNumber_example"; // String | 
try {
    QueryInvoiceAsyncStatusResponse result = apiInstance.queryInvoiceAsyncStatus(queryElementReferenceNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneZapytaniaApi#queryInvoiceAsyncStatus");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **queryElementReferenceNumber** | **String**|  |

### Return type

[**QueryInvoiceAsyncStatusResponse**](QueryInvoiceAsyncStatusResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="queryInvoiceSync"></a>
# **queryInvoiceSync**
> QueryInvoiceSyncResponse queryInvoiceSync(body, pageSize, pageOffset)

Zapytanie o faktury

Zapytanie o faktury

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneZapytaniaApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneZapytaniaApi apiInstance = new InterfejsyInteraktywneZapytaniaApi();
QueryInvoiceRequest body = new QueryInvoiceRequest(); // QueryInvoiceRequest | 
Integer pageSize = 56; // Integer | 
Integer pageOffset = 56; // Integer | 
try {
    QueryInvoiceSyncResponse result = apiInstance.queryInvoiceSync(body, pageSize, pageOffset);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneZapytaniaApi#queryInvoiceSync");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**QueryInvoiceRequest**](QueryInvoiceRequest.md)|  |
 **pageSize** | **Integer**|  | [enum: ]
 **pageOffset** | **Integer**|  | [enum: ]

### Return type

[**QueryInvoiceSyncResponse**](QueryInvoiceSyncResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

