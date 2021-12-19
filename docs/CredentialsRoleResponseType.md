# CredentialsRoleResponseType

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**roleType** | [**RoleTypeEnum**](#RoleTypeEnum) |  | 
**roleDescription** | **String** |  | 

<a name="RoleTypeEnum"></a>
## Enum: RoleTypeEnum
Name | Value
---- | -----
OWNER | &quot;owner&quot;
INVOICE_READ | &quot;invoice_read&quot;
INVOICE_WRITE | &quot;invoice_write&quot;
PAYMENT_CONFIRMATION_WRITE | &quot;payment_confirmation_write&quot;
CREDENTIALS_READ | &quot;credentials_read&quot;
CREDENTIALS_MANAGE | &quot;credentials_manage&quot;
SELF_INVOICING | &quot;self_invoicing&quot;
TAX_REPRESENTATIVE | &quot;tax_representative&quot;
ENFORCEMENT_OPERATIONS | &quot;enforcement_operations&quot;
COURT_BAILIFF | &quot;court_bailiff&quot;
ENFORCEMENT_AUTHORITY | &quot;enforcement_authority&quot;
