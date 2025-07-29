FROM bellsoft/liberica-openjdk-alpine:21
ADD ["build/libs/star-wars-1.0-SNAPSHOT.jar", "/opt/star-wars/"]
ENTRYPOINT ["java", "-jar", "/opt/star-wars/star-wars-1.0-SNAPSHOT.jar"]
EXPOSE 8763