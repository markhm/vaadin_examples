# Vaadin Examples

A repository with examples based on Vaadin Flow 14 and the simple Beverage Buddy app. 

Initial aim is to provide a good example of D3js integration with loading backing data from the Vaadin application via a separate servlet. Other examples as separate views would be much appreciated.  

### Characteristics
- Based on Vaadin 14 with Spring Boot
- Uses JAR-based packaging, so static resources (images, JavaScript files) are in `src/main/resources/META-INF/resources`
- The property file is filtered, see the Maven pom.xml and `src/main/resources/env/application.dev.properties`, etc.
- Internationalization with UTF-8-based resource files  

### To get started
- To build: `mvn package`
- To run: `mvn spring-boot:run`
- Then, open: http://localhost:8081

### Backlog / issues
- Align the D3 svg to the left, iso to the center.
- D3 example sometimes disappears for an unknown reason
- Servlet creation: https://vaadin.com/forum/thread/17784969

### Learnings
- This is 