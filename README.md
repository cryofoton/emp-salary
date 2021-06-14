# Employee Salary API

## Requirements

- Java 11 JDK
- Apache Maven 3 (to build the app)
- Docker (if you want to build and run the app in container)

## Setup

To clone and build this application, you'll need [Git](https://git-scm.com) and [Maven](https://maven.apache.org) installed on your computer. From your command line:

```bash
# Clone this repository
$ git clone https://github.com/cryofoton/emp-salary.git

# Go into the repository
$ cd emp-salary

# Install dependencies
$ mvn clean package

# Run the app
$ java -jar target/emp-salary-0.0.1-SNAPSHOT.jar

```

From there, you can access the API via url [http://localhost:8080](http://localhost:8080) using Postman, Insomnia, etc.

## Docker Images

You can also build and run the app in Docker with the provided Dockerfile

```bash
# Build docker image
$ docker build -t cryofoton/emp-salary .

# Run the image
$ docker run --name emp-salary -p 8080:8080 -d cryofoton/emp-salary

```

From there, you can access the API via url [http://localhost:8080](http://localhost:8080) using Postman, Insomnia, etc.
