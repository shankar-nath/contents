# java-poc
This is a template automation project with Java and Selenium.

There are 2 options to run the project.

**1. MacBook M1
**

_ Install jdk 17 or 18.

_ Install maven.

_ Install ChromeDriver and Chrome.

_ Go to project and run: mvn test
 
 
 
 
**2. Non MacBook M1
**

_ Install Docker.

_ Run: docker build -t java-poc .

_ Run: docker run -it -w /app -v $(pwd):/app java-poc:latest bash

_ Run: mvn test

Now you can continue working on the project.

