## Test task

## Goals
Configure the framework for creating and running UI autotests on the portal

### Requirements

#### environment
* `linux system (ubuntu, mint or other)`
* `intellij idea` https://www.jetbrains.com/ru-ru/idea/download
* `from java 1.8`

#### dependency

* `selenide`
* `junit5`
* `gradle`
* `allure`
* `webDriverManager`
* `log4j`
* `logback`
* `slf4j`
* `lombok`

### Variables

java: [Version: 8]

* Install - ``sudo apt-get install openjdk-8-jdk`` or ``sudo apt-get install openjdk-1.8.0-jdk``
* And set variable environment JAVA_HOME - export ``JAVA_HOME=/usr/lib/jvm/java-8-openjdk``

gradle: [Version: LATEST]
* Install - https://gradle.org/install/
#### OR
* You could use the ppa or SDKMAN for the almost-always latest version
  https://launchpad.net/~cwchien/+archive/ubuntu/gradle
* ``sudo add-apt-repository ppa:cwchien/gradle``
* ``sudo apt-get update``
* ``sudo apt upgrade gradle``

## Instructions

```git clone git@github.com:Truewaydm/vismeTest.git```

## How to run tests:

### Run tests:
#### if you have project structure that looks like this:
```
root
|-module-a
```
Run all tests

* ```gradle clean test```

* ```./gradlew clean test```

### Generate allure report: [https://docs.qameta.io/](https://docs.qameta.io/)

* ```./gradlew allureServe```

* ```./gradlew allureReport```

## Test results location:

```Test results will be generated in folder: /build/test-results```