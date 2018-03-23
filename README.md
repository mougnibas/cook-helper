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

Source encoding is UTF-8 (without BOM) with "CR LF" (windows) end of line caracters.


# Requirements

* Maven 3.5.2
* JDK 1.8
* Windows 10 (amd64)
* Docker 17.12.0 (or higher)
* DOCKER_HOST environment variable (example : tcp://localhost:2375 or tcp://localhost:2376)

# Setup

## Maven

1) Set JAVE_HOME environment variable to point to a JDK 1.8 install directory.
1) Get and unzip a Maven 3.5.2.
1) Add the "bin" maven directory to the user path.

## Eclipse

### Oxygen Release

1) Get an eclipse oxygen release for Java EE.
1) Checkout the git repository.
1) Import the projects in eclipse as "Existing Maven Projects".

### Eclipse checkstyle plugin

1) Open http://eclipse-cs.sourceforge.net in eclipse "Internal Web Brower".
1) Drag & Drop the "install" button into eclipse, then follow the installation instructions.
1) Right-clic on projects, then "Checkstyle / Create formater profile".
1) Window / Preferences / Java / Code Style / Clean Up / Activate profile, then select the new one ("eclipse-cs cook-helper")
1) Window / Preferences / Java / Code Style / Formater / Activate profile, then select the new one ("eclipse-cs cook-helper")

### Eclipse PMD plugin

1) Add this update site : https://dl.bintray.com/pmd/pmd-eclipse-plugin/updates/
1) Install "PMD for eclipse 4", then follow installation instructions.

### Eclipse FindBugs plugin

1) Add this update site : http://findbugs.cs.umd.edu/eclipse
1) Install "FindBugs Feature", then follow installation instructions.
1) Window / Preferences / Java / FindBugs
1) Minimum rank to report : 15
1) Minimal priority : Low
1) Apply
1) Right-clic on ejb/war/jar projects, then "Find Bugs / Find Bugs".

### JUnit

1) Get and unzip a WildFly 11.0.0.Final version.
1) Add "JBOSS_HOME" environment variable, to point to the unzip wildfly.

### Application Server

1) Window / Preferences / Server / Runtime Environments / Add / Red Hat JBoss Middleware / JBoss AS, Wildfy & EAP Server Tools
1) Wait for the install to complete.
1) Add / JBoss Community / WildFly 11 Runtime
1) Home direcetory : the previously unziped wildfly install

# Application lifecycle

## Package

`mvn clean verify`

*(Don't use eclipse to run maven, because I have weird behavior with it.)*

## Run

`docker run --rm -it -p 8080:8080 mougnibas/cook-helper-recipe`

## Browse

### Recipe

Plain text :
* http://localhost:8080/cook-helper-recipe/list
* http://localhost:8080/cook-helper-recipe/recipe?name=Minestrone

Webservices :
* http://localhost:8080/cook-helper-recipe/recipe/
* http://localhost:8080/cook-helper-recipe/recipe/Minestrone

### Shop list

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
