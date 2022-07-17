Initial setup
- ``aws iam create-role --role-name lambda-role --assume-role-policy-document file://./aws/role.json``
- ``aws lambda create-function --function-name starcraft_poll``

Upload jar to AWS

- ``mvn clean``
- ``mvn package``
- ``aws lambda update-function-code --function-name starcraft_poll --zip-file fileb://./target/starcraft_poll-0.0.1-SNAPSHOT.jar``