##Homework 6

In this project I have implemented a calculator function and deployed it on AWS Lambda and connected it to AWS API Gateway.
Once the APIs are exposed I have showed how to connect to the APIs using a rest client library.

####Environment
    
    Language : Java
    Build : Gradle
    Frameworks : Serverless, Jersey
    Cloud provider : AWS

####Setup

    a. Install Java
    b. Install Gradle
    c. Install Node.js
    d. Install serverless : npm install -g serverless


####Implementation Details

######AWS Lambda function

I have used the AWS Lambda Java Library to implement the calculator function.

The calculator function and its handler is configured in the serverless.yml file as follows:

    functions:
      calculator:
        handler: com.serverless.JavaLambda 
        
The service name is configured as follows:

    service: lambdaservice
    
Steps to deploy and integrate the API Gateway:
    
    1. gradle clean build
    
    2. Setup the AWS credentials for serverless as instructed here - https://serverless.com/framework/docs/providers/aws/guide/credentials/
     
    3. serverless deploy - This will deploy the lambda function to the AWS account
    
    4. API Gateway
        a. Go to the function page on the AWS console
        b. Add the trigger for API Gateway and save
        c. Access the API through the obtained end point
        
   
#####API specification

Pass the following as query parameters:

       operand1=<num1>
       operand2=<num2>
       operator=add     [add/subtract/multiply/divide]
       
       Example: API_URL?operand1=2&operand2=5&operator=add

#####How to access the API Gateway

The calculator API can be accessed using a REST client (POSTMAN) or through the com.serverless.client.CalculatorClient class in the project.

