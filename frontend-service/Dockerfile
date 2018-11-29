FROM jeanblanchard/java:8
MAINTAINER Philipp Waibel

ADD target/frontend-service.jar /frontend-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/frontend-service.jar"]