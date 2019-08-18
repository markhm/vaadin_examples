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
- Open a browser to http://localhost:8081

### Backlog / issues for the D3 Tree example
- ~~The D3Anchor should be placed to the left in the middle, iso to the center, which allows the tree to nicely 'fan out' to the right~~
- D3 example disappears a few seconds for an unknown reason
- ~~The D3 example is not removed when selecting a different tab~~
- Open question on data integration via a servlet: https://vaadin.com/forum/thread/17784969
- Colours/styles should be modified so D3 and Vaadin do not visually clash 

### Other examples to be added
- [Tabs to show different pages](https://vaadin.com/components/vaadin-tabs/java-examples) 

### Acknowledgement
Kind contributions were received from: 
- [Marco Ebbinghaus](http://www.twitter.com/codinghaus)  