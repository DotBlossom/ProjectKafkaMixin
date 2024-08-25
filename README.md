# SNS 프로젝트 backend 파트

## application.properties 데이터베이스 구현 코드
```commandline
spring.application.name=delta
spring.datasource.driver-class-name =com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/DeltaDB?createDatabaseIfNotExist=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.datasource.username={id}
spring.datasource.password={password}
spring.jpa.hibernate.ddl-auto=update
```




