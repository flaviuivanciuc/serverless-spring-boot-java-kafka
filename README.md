# Kafka Consumer written in Java 17 and Spring Boot 3 on AWS Lambda

SpringBoot project with Java 17 deployed to AWS Lambda using the Serverless Framework.
The application context holds a @Bean named `pollMessageFromKafka` which serves as the function definition.
Once an event of type `KafkaEvent` triggers the Lambda Function, the bean is being invoked and
it processes the received message.

- [🏁 Getting started](#getting-started)
  - [📋 Requirements](#requirements)
    - [🔨 Local Setup](#local-setup)
- [🚀 Deployment](#deployment)
  - [Jobs](#jobs)
- [🌥️ Cloud Environment](#cloud-environment)
  - [🧪 Test AWS Lambda](#test-aws-lambda)
- [📚 Useful Docs](#useful-docs)

<!-- TOC --><a name="getting-started"></a>

## 🏁 Getting started

<!-- TOC --><a name="requirements"></a>

### 📋 Requirements

- Java 17
- Node

<!-- TOC --><a name="local-setup"></a>

#### 🔨 Local Setup

1. Install Node Dependencies

```shell
make install
```

2. Build the Project

```shell
make build-app
```

3. Invoke Locally

```shell
make invoke-local
```

<!-- TOC --><a name="deployment"></a>

## 🚀 Deployment

<!-- TOC --><a name="jobs"></a>

### Jobs

- [Deploy](.github/workflows/serverless-java-spring-boot-kafka-deploy.yml)

- [Remove](.github/workflows/serverless-java-spring-boot-kafka-remove.yml)

> 📒 Note
>
> Make sure to set both `AWS_ACCESS_KEY_ID` and `AWS_SECRET_ACCESS_KEY` to the Secrets and Variables Section of your
> GitHub Repository

<!-- TOC --><a name="cloud-environment"></a>

## 🌥️ Cloud Environment

<!-- TOC --><a name="test-aws-lambda"></a>

### 🧪 Test AWS Lambda

In the AWS Console Management 👉🏻 Lambda 👉🏻 Test Tab 👉🏻 paste the following snippet and execute the function:

```json
{
  "eventSource": "SelfManagedKafka",
  "bootstrapServers": "b-2.demo-cluster-1.a1bcde.c1.kafka.us-east-1.amazonaws.com:9092,b-1.demo-cluster-1.a1bcde.c1.kafka.us-east-1.amazonaws.com:9092",
  "records": {
    "mytopic-0": [
      {
        "topic": "mytopic",
        "partition": 0,
        "offset": 15,
        "timestamp": 1545084650987,
        "timestampType": "CREATE_TIME",
        "key": "abcDEFghiJKLmnoPQRstuVWXyz1234==",
        "value": "SGVsbG8sIHRoaXMgaXMgYSB0ZXN0Lg==",
        "headers": [
          {
            "headerKey": [104, 101, 97, 100, 101, 114, 86, 97, 108, 117, 101]
          }
        ]
      }
    ]
  }
}
```

This will trigger the Lambda Function using a look-a-like KafkaEvent event.

<!-- TOC --><a name="useful-docs"></a>

## 📚 Useful Docs

- https://www.serverless.com/examples/aws-java-spring-cloud-function-demo
- https://docs.spring.io/spring-cloud-function/reference/adapters/aws-intro.html
- https://docs.aws.amazon.com/lambda/latest/dg/with-kafka.html
- https://www.serverless.com/framework/docs/providers/aws/events/kafka
