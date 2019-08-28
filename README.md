# Vaadin Examples

A repository with examples based on Vaadin Flow 14 and the simple Beverage Buddy app.

Initial aim is to provide a good example of D3js integration where backing data from the Vaadin application is loaded via a separate servlet. Other examples are welcome.

### Characteristics
- Based on Vaadin 14 and Spring Boot
- Uses JAR-based packaging, so static resources (images, JavaScript files) are in `src/main/resources/META-INF/resources/frontend`
- The property file is filtered, see the Maven `pom.xml` and `src/main/resources/env/application.dev.properties`, etc.
- Internationalization supports UTF-8-based resource files  

### To get started
- To build: `mvn package`
- To run: `mvn spring-boot:run`
- Open a browser to http://localhost:8081/app

### Backlog / issues for the D3 Tree example
- Drag/Move events are not available under Vaadin, see this [post](https://vaadin.com/forum/thread/17808926/integrating-d3-javascript-drag-zoom-blocked-by-vaadin).
- The proper node/item for JavaScript click results should be available in Java
- Nodes should be colorable from Java
- _other_

### Other examples to be added
- tbd 

### Acknowledgement
Kind contributions were received from: 
- [Marco Ebbinghaus](http://www.twitter.com/codinghaus)  
