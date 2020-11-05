
# JSON Place Holder API Automation Testing

Endpoint : https://jsonplaceholder.typicode.com/

**Tools Used:**
1. Java 11
2. REST Assured v4.2.0
3. Maven
4. TestNG Framework v7.0.0
5. Circle CI

**Test Cases Automated:**

1. Validate GET “users” with Invalid Username
2. Validate GET “posts” method with Invalid Post ID
3. Validate Email format in the Comments for posts created by user “Delphine”

**Command to run the test :**

>mvn integration-test

**Overview:**

1. CircleCI "config.yml" file has been setup and the project can be executed in CircleCI directly
2. Once the execution is completed in CircleCI,the emailable html report can be found under Artifacts.
