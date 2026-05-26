FROM gradle:jdk25
WORKDIR /app
COPY . .
EXPOSE 3000
RUN gradle clean build
RUN ls -al  build/libs
CMD ["java", "-jar", "build/libs/auth-service-0.0.1-SNAPSHOT.jar"]