# InterfejsyInteraktywnePatnociApi

All URIs are relative to *https://ksef-test.mf.gov.pl/api*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getPaymentIdentifierReferenceNumbers**](InterfejsyInteraktywnePatnociApi.md#getPaymentIdentifierReferenceNumbers) | **GET** /online/Payment/Identifier/GetReferenceNumbers/{PaymentIdentifier} | Pobranie listy faktur dla identyfikatora płatności
[**requestPaymentIdentifier**](InterfejsyInteraktywnePatnociApi.md#requestPaymentIdentifier) | **POST** /online/Payment/Identifier/Request | Wygenerowanie identyfikatora płatności

<a name="getPaymentIdentifierReferenceNumbers"></a>
# **getPaymentIdentifierReferenceNumbers**
> GetPaymentIdentifierReferenceNumbersResponse getPaymentIdentifierReferenceNumbers(paymentIdentifier)

Pobranie listy faktur dla identyfikatora płatności

Pobranie listy faktur dla identyfikatora płatności

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywnePatnociApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywnePatnociApi apiInstance = new InterfejsyInteraktywnePatnociApi();
String paymentIdentifier = "paymentIdentifier_example"; // String | 
try {
    GetPaymentIdentifierReferenceNumbersResponse result = apiInstance.getPaymentIdentifierReferenceNumbers(paymentIdentifier);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywnePatnociApi#getPaymentIdentifierReferenceNumbers");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **paymentIdentifier** | **String**|  |

### Return type

[**GetPaymentIdentifierReferenceNumbersResponse**](GetPaymentIdentifierReferenceNumbersResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="requestPaymentIdentifier"></a>
# **requestPaymentIdentifier**
> RequestPaymentIdentifierResponse requestPaymentIdentifier(body)

Wygenerowanie identyfikatora płatności

Wygenerowanie identyfikatora płatności

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.InterfejsyInteraktywnePatnociApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure API key authorization: SessionToken
ApiKeyAuth SessionToken = (ApiKeyAuth) defaultClient.getAuthentication("SessionToken");
SessionToken.setApiKey("YOUR API KEY");
// Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
//SessionToken.setApiKeyPrefix("Token");

InterfejsyInteraktywnePatnociApi apiInstance = new InterfejsyInteraktywnePatnociApi();
RequestPaymentIdentifierRequest body = new RequestPaymentIdentifierRequest(); // RequestPaymentIdentifierRequest | 
try {
    RequestPaymentIdentifierResponse result = apiInstance.requestPaymentIdentifier(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling InterfejsyInteraktywnePatnociApi#requestPaymentIdentifier");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RequestPaymentIdentifierRequest**](RequestPaymentIdentifierRequest.md)|  |

### Return type

[**RequestPaymentIdentifierResponse**](RequestPaymentIdentifierResponse.md)

### Authorization

[SessionToken](../README.md#SessionToken)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

