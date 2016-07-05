# CF-Demo
This is a tiny one endpoint web application, in Java language, that does "String Concatenation As a Service" and does the following: 
* Requires the API key send as part of header X-Auth-Key 
* Has two get parameters. pre and post which are the strings concatenated together. 
* Is an API that by can be called by any REST client 

## Live instance
Use REST client like [Postman] (https://www.getpostman.com/) to access the endpoint
https://cloudycat.herokuapp.com/concat?pre=hello&post=world

## Pre requisites
Java 8

## Steps to setup, deploy and run locally

1. git clone https://github.com/shrikant0013/CF-Demo.git
2. cd cloudycat
3. ./gradlew build
4. java -jar build/libs/cloudycat-0.0.1-SNAPSHOT.jar
5. REST endpoint will be available at http://localhost:8080/concat
6. visit http://localhost:8080/concat?pre=abc&post=123

## Open-source libraries used
* [Spring boot] (http://projects.spring.io/spring-boot)
* [Gradle build system] (http://gradle.org) 
* [Java PBKDF2] (https://gist.github.com/jtan189/3804290)
