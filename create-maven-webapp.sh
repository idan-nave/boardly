#!/bin/bash

# Project name
PROJECT_NAME="MyWebApp"

# Create base project directory
mkdir -p $PROJECT_NAME

# Navigate into the project directory
cd $PROJECT_NAME || exit

# Create Maven standard directories
mkdir -p src/main/java
mkdir -p src/main/resources
mkdir -p src/main/webapp/WEB-INF
mkdir -p src/test/java

# Create essential files
echo "Creating pom.xml..."
cat <<EOF >pom.xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>
    <artifactId>$PROJECT_NAME</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <dependencies>
        <!-- Servlet API -->
        <dependency>
            <groupId>jakarta.servlet</groupId>
            <artifactId>jakarta.servlet-api</artifactId>
            <version>5.0.0</version>
            <scope>provided</scope>
        </dependency>

        <!-- Jackson for JSON processing -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.15.0</version>
        </dependency>
    </dependencies>
</project>
EOF

echo "Creating web.xml..."
cat <<EOF >src/main/webapp/WEB-INF/web.xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" version="4.0">
    <display-name>$PROJECT_NAME</display-name>
</web-app>
EOF

echo "Project structure created successfully."

# List the project structure
echo "Project hierarchy:"
tree $PROJECT_NAME

