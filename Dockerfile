FROM eclipse-temurin:18-alpine
MAINTAINER passarelli.dev
COPY target/PersonaggioGuesser-0.0.1-SNAPSHOT.jar personaggio-guesser-backend.jar
ENTRYPOINT ["java","-jar","/personaggio-guesser-backend.jar"]
