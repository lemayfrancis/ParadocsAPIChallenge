# ParadocsAPIChallenge
Create a basic REST API based on requirements in less than 2 days

### Requisites
* Maven 3.6
* Java 13
* A local running MongoDB service
  * The app use MongoDB default port __27017__.
  * The app use db __test__.

### Install
```$xslt
mvn clean install
```

### Run
```$xslt
mvn exec:java
```
Use the default port 8080.

### Run tests
```$xslt
mvn test
```

### Code coverage
Run the following command:
```$xslt
mvn jacoco:report
```
Then open _"target/site/jacoco/index.html"_

### Manual code format
```$xslt
mvn git-code-format:format-code -DglobPattern=**/*
```