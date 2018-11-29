FROM jeanblanchard/java:8
MAINTAINER Philipp Waibel

ADD target/temperature-service.jar /temperature-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/temperature-service.jar"]