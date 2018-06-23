```
© Copyright 2017-2018 Yoann MOUGNIBAS

This file is part of Cook-Helper.

Cook-Helper is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Cook-Helper is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Cook-Helper. If not, see <http://www.gnu.org/licenses/>
```

# Project informations

## General

The aim to this project is to make the use of the KitchenAid Cook processor easier.

There is a basic "recipe brower" function.

There is also a "food buy" function, where you select some recipe, and it build a list of raw food.

## Misc

Source encoding is UTF-8 (without BOM) with "CR LF" (windows) end of line characters.

SonarQube instance available on [sonarcloud.io](https://sonarcloud.io/organizations/mougnibas-github/projects) (github project credentials).

# Requirements

* Maven 3.5.3
* JDK 10.0.1
* Windows 10 (amd64)
* Docker 18.03.1 (or higher)
* DOCKER_HOST environment variable (example : tcp://localhost:2375 or tcp://localhost:2376)

# Setup

## Maven

1) Set JAVE_HOME environment variable to point to the JDK install directory.
1) Get and unzip a Maven.
1) Add the "bin" maven directory to the user path.

## Eclipse

### Oxygen Release

1) Get an eclipse oxygen release for Java EE.
1) Checkout the git repository.
1) Import the projects in eclipse as "Existing Maven Projects".

### Eclipse checkstyle plugin (8.8.0 plugin, based on Checkstyle 8.8)

1) Open http://eclipse-cs.sourceforge.net in eclipse "Internal Web Brower".
1) Drag & Drop the "install" button into eclipse, then follow the installation instructions.
1) Right-clic on projects, then "Checkstyle / Create formater profile".
1) Window / Preferences / Java / Code Style / Clean Up / Activate profile, then select the new one ("eclipse-cs cook-helper")
1) Window / Preferences / Java / Code Style / Formater / Activate profile, then select the new one ("eclipse-cs cook-helper")

### Eclipse Sonarlint plugin (3.4 plugin)

1) Installation
   1) Open https://marketplace.eclipse.org/content/sonarlint in eclipse "Internal Web Brower".
   1) Drag & Drop the "install" button into eclipse, then follow the installation instructions.
1) Configuration
   1) Add a "SonarQube Servers" and a "SonarLint On-The-Fly" view
   1) On "SonarQube Servers" view, connect to the sonarcloud server
      1) "Connect to a SonarQube server
	  1) "Sonarcloud"
	  1) Token : A secret one !
	  1) Organization : mougnibas-github
   1) Eclipse project binding
      1) Select all eclipse project / right clic / SonarLint / Bind to a sonarqube project
	  1) Auto bind selected projects / Finish (must run an initial sonar:sonar before)
   1) On "SonarQube Servers" view / right clic on the server / Update all project bindings
1) Full scan
   1) Select all eclipse project
   1) Right clic
   1) Analyze
1) On the fly analyze
   1) Just open a file!

### Application Server

*This step is optional, as most of the tests should run in a dedicated JUnit Integration test.

1) Window / Preferences / Server / Runtime Environments / Add / Red Hat JBoss Middleware / JBoss AS, Wildfy & EAP Server Tools
1) Wait for the install to complete.
1) Add / JBoss Community / WildFly 11 Runtime
1) Home direcetory : the previously unziped wildfly install

# Application lifecycles

## Package

`mvn clean package`

*(Don't use eclipse to run maven, because I have weird behavior with it.)*

## Run

`docker network create cook_helper`

`docker run --rm -it -p 8080:8080 --network=cook_helper --name cook-helper-recipe-microprofile   mougnibas/cook-helper-recipe-microprofile`

`docker run --rm -it -p 8090:8090 --network=cook_helper --name cook-helper-recipe-ui             mougnibas/cook-helper-recipe-ui`

`docker run --rm -it -p 8100:8100 --network=cook_helper --name cook-helper-shoplist-microprofile mougnibas/cook-helper-shoplist-microprofile`

`docker network rm     cook_helper`

## Verify

### Run all quality check code

`mvn clean verify`

### Run a sonarqube analyze

`mvn clean verify sonar:sonar -Dsonar.login=YourSecretToken`


## Browse

### Recipe microservice

Webservices :
* http://localhost:8080/
* http://localhost:8080/{recipe name}

Plain text :
* http://localhost:8090/list
* http://localhost:8090/recipe?name=Minestrone

### Shop list microservice

Webservices :
* http://localhost:8100/?name=Minestrone&name=Risotto&name=...

Plain text :
TODO

## Deploy

Deploy docker image :

`mvn clean deploy`


# TODOs

1) Create an Oomph setup.
1) Find a way to use FindBugs in eclipse, like checkstyle and PMD plugin, with auto config using maven.
1) maven-javadoc-plugin show errors on build.
1) Don't use a custom assembly : Try to use the default one and exclude maven and eclipse stuff.
1) Add another module to add some recipes for building a "weekly bill of materials"
