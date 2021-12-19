# InterfejsyInteraktywneFakturyApi

All URIs are relative to *https://ksef-test.mf.gov.pl/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getInvoice**](InterfejsyInteraktywneFakturyApi.md#getInvoice) | **GET** /online/Invoice/Get/{KSeFReferenceNumber} | Pobranie faktury
[**sendInvoice**](InterfejsyInteraktywneFakturyApi.md#sendInvoice) | **PUT** /online/Invoice/Send | Wysyłka faktury
[**statusInvoice**](InterfejsyInteraktywneFakturyApi.md#statusInvoice) | **GET** /online/Invoice/Status/{InvoiceElementReferenceNumber} | Sprawdzenie statusu wysłanej faktury

<a name="getInvoice"></a>
# **getInvoice**
> Object getInvoice(kseFReferenceNumber)

Pobranie faktury

Pobranie faktury

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneFakturyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneFakturyApi apiInstance = new InterfejsyInteraktywneFakturyApi();
String kseFReferenceNumber = "kseFReferenceNumber_example"; // String | 
try {
    Object result = apiInstance.getInvoice(kseFReferenceNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneFakturyApi#getInvoice");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **kseFReferenceNumber** | **String**|  |

### Return type

**Object**

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream, application/json

<a name="sendInvoice"></a>
# **sendInvoice**
> SendInvoiceResponse sendInvoice(body)

Wysyłka faktury

Wysyłka faktury 

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneFakturyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneFakturyApi apiInstance = new InterfejsyInteraktywneFakturyApi();
SendInvoiceRequest body = new SendInvoiceRequest(); // SendInvoiceRequest | 
try {
    SendInvoiceResponse result = apiInstance.sendInvoice(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneFakturyApi#sendInvoice");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SendInvoiceRequest**](SendInvoiceRequest.md)|  |

### Return type

[**SendInvoiceResponse**](SendInvoiceResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="statusInvoice"></a>
# **statusInvoice**
> StatusInvoiceResponse statusInvoice(invoiceElementReferenceNumber)

Sprawdzenie statusu wysłanej faktury

Sprawdzenie statusu wysłanej faktury

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywneFakturyApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywneFakturyApi apiInstance = new InterfejsyInteraktywneFakturyApi();
String invoiceElementReferenceNumber = "invoiceElementReferenceNumber_example"; // String | 
try {
    StatusInvoiceResponse result = apiInstance.statusInvoice(invoiceElementReferenceNumber);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywneFakturyApi#statusInvoice");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **invoiceElementReferenceNumber** | **String**|  |

### Return type

[**StatusInvoiceResponse**](StatusInvoiceResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

