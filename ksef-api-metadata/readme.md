## Generowanie klienta

````shell
java -jar swagger-codegen-cli-3.0.30.jar generate \ 
    -i src/main/resources/KSeF-common.yaml \
    -l java \
    --api-package io.alapierre.ksef.common.client \
    --model-package io.alapierre.ksef.common.client.model \
    -o gen/common-client
````

````shell
java -jar swagger-codegen-cli-3.0.30.jar generate \ 
    -i src/main/resources/KSeF-online.yaml \
    -l java \
    --api-package io.alapierre.ksef.online.client \
    --model-package io.alapierre.ksef.online.client.model \
    -o gen/online-client
````

````shell
java -jar swagger-codegen-cli-3.0.30.jar generate \ 
    -i src/main/resources/KSeF-batch.yaml \
    -l java \
    --api-package io.alapierre.ksef.batch.client \
    --model-package io.alapierre.ksef.batch.client.model \
    -o gen/batch-client
````
