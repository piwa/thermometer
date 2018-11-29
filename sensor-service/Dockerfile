FROM jeanblanchard/java:8
MAINTAINER Philipp Waibel

ADD target/sensor-service.jar /sensor-service.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/sensor-service.jar"]