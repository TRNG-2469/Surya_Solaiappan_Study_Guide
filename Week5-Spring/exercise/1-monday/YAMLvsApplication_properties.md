YAML stands for YAML Aint Markup Language. YAML follows a structured format with indexes. It can be used with Docker and Kubernetes. 


Application.properties follows a structured approach. And has higher precedence if a YAML file is also present.

# Examples

## application.properties

spring.datasource.url=jdbc:mysql://localhost:3306/db
spring.datasource.username=root
spring.datasource.password=secret


## app.yaml

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/db
    username: root
    password: secret
