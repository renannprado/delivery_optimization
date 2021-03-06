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

Go to **bin** directory inside of JBOSS_HOME and execute the below script (or relevant shell script if you are using linux):

```
#!shellscript
add-user.bat
```

Choose the **Management User** and create a user called **admin** with password **admin** - this will save configuration time as it's default user/password configure in the project.

Now it is time to start the server. **Make sure that you have ports 8080 and 9990 available in your machine otherwise Wildfly won't start. Changing the default port configuration is not covered here, though you can easily figure out by opening the <JBOSS_HOME>/standalone/configuration/standalone.xml file.**

In order to start using the **standalone** profile, execute the bloe command (still in **bin** folder of Wildfly):

```
#!shellscript
standalone.bat
```

After starting you should see a message similar to the below one:

```
#!
06:55:58,394 INFO  [org.jboss.as] (Controller Boot Thread) WFLYSRV0025: WildFly Full 9.0.1.Final (WildFly Core 1.0.1.Final) started in 9113ms - Started 487 of 671 services (237 services are lazy, passive or on-demand)
```

Now try to access http://localhost:8080/ , if everything is ok, you should see "Welcome to Wildfly" message.


## Database configuration ##

This application creates [H2 embedded database](http://h2database.com/) datasource during deployment, so you don't need to setup anything related to database. All you need is to make sure that the user that will execute Wildfly has permission to write into his own home folder :)

## How to run tests ##

**Wildfly must be running.**

By default the tests are skiped, so you need to enable the **arq-wildfly-remote-web** profile.
Go to the root folder of the repository and run the below command:

```
#!shellscript
mvn clean package -P arq-wildfly-remote-web
```

## Deployment instructions##

**Wildfly must be running.**


Go to the root folder of the repository and run the below command:

```
#!shellscript
mvn clean install
```

If you see the below message in the console of Wildfly, then the deploy must have been successful:

```
#!
07:01:18,183 INFO  [org.wildfly.extension.undertow] (ServerService Thread Pool -- 64) WFLYUT0021: Registered web context: /walmart
```

Now you should be able to access the application going to http://localhost:8080/walmart

If you need to call the shortest path algorithm directly for some reason, you can use the below URL as sample:

http://localhost:8080/walmart/rest/getShortestPath?mapName=sp&from=a&to=b&autonomy=10&gasPrice=2

### List of technologies ###

## Language ##

* ![Java Logo](http://www.oracle.com/ocom/groups/public/@otn/documents/digitalasset/2034284.png) Java 

## Application Server ##

* ![Wildfly Application Server](https://www.jboss.org/dms/wildfly_splash/splash_wildflylogo_small.png) [Wildfly](http://wildfly.org/)

## Build tool ##

* ![Apache Maven Logo](http://maven.apache.org/images/logos/maven-feather.png) [Apache Maven](http://maven.apache.org)

## Continuous Integration ##

* ![Jenkins Logo](https://jenkins-ci.org/sites/default/files/jenkins_logo.png) [Jenkins CI](https://jenkins-ci.org/)

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