# QueryInvoiceAsyncStatusResponse

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**timestamp** | [**OffsetDateTime**](OffsetDateTime.md) |  | 
**referenceNumber** | **String** |  | 
**processingCode** | **Integer** |  | 
**processingDescription** | **String** |  | 
**elementReferenceNumber** | **String** |  | 
**numberOfParts** | **Integer** |  | 
**numberOfElements** | **Long** |  | 
**divisionType** | [**DivisionTypeEnum**](#DivisionTypeEnum) |  | 
**partList** | [**List&lt;InvoiceDivisionPlainPartType&gt;**](InvoiceDivisionPlainPartType.md) |  | 

<a name="DivisionTypeEnum"></a>
## Enum: DivisionTypeEnum
Name | Value
---- | -----
HOUR | &quot;hour&quot;
DAY | &quot;day&quot;
WEEK | &quot;week&quot;
MONTH | &quot;month&quot;
YEAR | &quot;year&quot;
