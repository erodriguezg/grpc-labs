# GRPC-JAVA-LAB

##  ESTRUCTURA

**grpc-java-lab/**
* ├─ pom.xml                 ← POM raíz
* ├── **grpc-java-api/**          ← Contiene los `.proto` y genera los stubs
* │   └── pom.xml
* ├── **grpc-java-service/**      ← Microservicio gRPC (Spring Boot) que implementa los servicios 
* │   └── pom.xml
* ├── **grpc-java-web/**          ← Aplicación Web/BFF (Spring Boot MVC/REST) que consume el servicio gRPC
* │   └── pom.xml


## PLUGINS INTELLIJ IDEA
* PROTOBUF 2.1