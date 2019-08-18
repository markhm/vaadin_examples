# Vaadin Examples

A repository with examples based on Vaadin Flow 14. 

Initial aim is to provide a good example of D3js integration with loading back data from the Vaadin application via a separate servlet. 

### Characteristics
- Vaadin 14 with Spring Boot
- Uses JAR-based packaging, so resources are in 'src/main/resources/META-INF/resources'
- Property file filtering, see 'src/main/resources/env/application.dev.properties', etc.
- Internationalization support

### To get started
- mvn package
- mvn spring-boot:run
- open http://localhost:8081

### Backlog
- Align the D3 svg to the left, iso to the center.
- https://vaadin.com/forum/thread/17784969
