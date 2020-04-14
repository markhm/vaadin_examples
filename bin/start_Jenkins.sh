# https://stackoverflow.com/questions/55055488/jenkins-in-docker-cannot-connect-to-the-docker-daemon-at-unix-var-run-docke
# https://antonfisher.com/posts/2017/01/16/run-jenkins-in-docker-container-with-persistent-configuration/

docker run -p 8080:8080 --user root -v /var/run/docker.sock:/var/run/docker.sock blueocean:latest



# I am trying to build a Vaadin application in Docker, for which I need a container that contains NodeJS.

# docker run -v /Users/mark/.m2:/root/.m2 maven:3-jdk-12 mvn
# docker run -v /Users/mark/.m2:/root/.m2 -it maven:3-jdk-12 bash

# https://gist.github.com/mitchwongho/11266726



