
# Build
FROM maven:3-jdk-11 as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn -f ./pom.xml clean package -Dmaven.test.skip=true

# Package
FROM openjdk:11
WORKDIR /app
COPY db ./db
COPY --from=build /build/target/emp-salary-0.0.1-SNAPSHOT.jar ./emp-salary.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./emp-salary.jar"]
