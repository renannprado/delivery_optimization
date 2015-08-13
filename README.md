# Delivery Optmization #

[![Build Status](http://build.renann.me/buildStatus/icon?job=DeliveryOptimization)](http://build.renann.me/job/DeliveryOptimization/)

This application is a test for Walmart's job selection process.

This project is being continuously built and can be accessed through the following URL: http://dev.renann.me/walmart

Version **1.0.Final**

### Motivation (PT-BR) ###

Escolhi essas tecnologias, pois são com as quais gosto de trabalhar e acredito que contribuem para um desenvolvimento mais rápido e de qualidade.

### How do I get set up? ###

## Downloads ##


* Download and install [Java **8**](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* Download and extract [Wildfly **9.0.1**](http://download.jboss.org/wildfly/9.0.1.Final/wildfly-9.0.1.Final.zip)
* Download and extract [Apache Maven](http://apache.osuosl.org/maven/maven-3/3.3.3/binaries/apache-maven-3.3.3-bin.zip)


## Configuration ##

First you need to make sure you can call these commands from command line.

```
#!shellscript
java -version
```


```
#!shellscript
mvn -version
```

Even after being able to successfully call these commands, you **must** setup the following environment variables. Example:

**JAVA_HOME:** C:\Program Files\Java\jdk1.8.0_51

**MAVEN_HOME:** C:\development\maven\apache-maven-3.3.3

**JBOSS_HOME:**  C:\development\wildfly-9.0.1.Final

###

* Summary of set up
* Configuration
* Dependencies
* Database configuration
* How to run tests
* Deployment instructions

### List of technologies ###

## Language ##

* ![Java Logo](http://www.oracle.com/ocom/groups/public/@otn/documents/digitalasset/2034284.png) Java 

## Application Server ##

* ![Wildfly Application Server](https://www.jboss.org/dms/wildfly_splash/splash_wildflylogo_small.png) [Wildfly](http://wildfly.org/)

## Testing ##

* ![Arquillian Logo](http://arquillian.org/images/arquillian-icon-s.png?1438819492) [Arquillian](http://arquillian.org/)
* ![JUnit Logo](http://junit.org/images/junit-logo.png) [JUnit](http://junit.org)
## Front-end ##

* ![AngularJS Logo](https://angularjs.org/img/AngularJS-small.png) [AngularJS](http://angularjs.org)
* ![Bootstrap Logo](http://s10.postimg.org/mi11u4eg5/bootstrap.png) [Twitter's Bootstrap](http://getbootstrap.com/)

### Screen shots ###

* Screen shot of the screen where you register a new map

![Screen shot of the application](http://s16.postimg.org/406y2sg05/print_test_1.png)

* Screen shot of the screen where you trace the best route

![Screen shot of the application](http://s16.postimg.org/8nd04k3d1/print_test_2.png)