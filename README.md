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


# General project info

The aim to this project is to make the use of the KitchenAid Cook processor easier.
There is a basic "recipe brower" function.
There is also a "food buy" function, where you select some recipe, and it build a list of raw food.



# Requirements

Maven 3.5.2
JDK 1.8
Windows 10 (amd64)
Docker 17.12.0 (or higher)
DOCKER_HOST environment variable (example : tcp://localhost:2375 or tcp://localhost:2376)



# Maven Setup
=====================
Set JAVE_HOME environment variable to point to a JDK 1.8 install directory.
Get and unzip a Maven 3.5.2.
Add the "bin" maven directory to the user path.


# Application package

mvn clean verify
(Don't use eclipse to run maven, because I have weird behavior with it.)



# Application run

docker run --rm -it -p 8080:8080 mougnibas/cook-helper-recipe



# Application browse

http://localhost:8080/cook-helper-recipe/list
http://localhost:8080/cook-helper-recipe/recipe?name=SomeRecipeName

http://localhost:8080/cook-helper-recipe/recipe/
http://localhost:8080/cook-helper-recipe/recipe/SomeRecipeName


# Application deploy

mvn clean deploy



# Eclipse Setup

Get an eclipse oxygen release for Java EE.
Checkout the git repository.
Import the projects in eclipse as "Existing Maven Projects".

Checkstyle plugin in eclipse
Open http://eclipse-cs.sourceforge.net in eclipse "Internal Web Brower".
Drag & Drop the "install" button into eclipse, then follow the installation instructions.
Right-clic on projects, then "Checkstyle / Create formater profile".
Window / Preferences / Java / Code Style / Clean Up / Activate profile, then select the new one ("eclipse-cs cook-helper")
Window / Preferences / Java / Code Style / Formater / Activate profile, then select the new one ("eclipse-cs cook-helper")

PMD plugin in eclipse
Add this update site : https://dl.bintray.com/pmd/pmd-eclipse-plugin/updates/
Install "PMD for eclipse 4", then follow installation instructions.

FindBugs
Add this update site : http://findbugs.cs.umd.edu/eclipse
Install "FindBugs Feature", then follow installation instructions.
Window / Preferences / Java / FindBugs
Minimum rank to report : 15
Minimal priority : Low
Apply
Right-clic on ejb/war/jar projects, then "Find Bugs / Find Bugs".

TODO :
Create an Oomph setup.
Find a way to use FindBugs in eclipse, like checkstyle and PMD plugin, with auto config using maven.



# Eclipse JUnit Setup

Get and unzip a WildFly 11.0.0.Final version.
Add "JBOSS_HOME" environment variable, to point to the unzip wildfly.



# Eclipse Application Server Setup

Window / Preferences / Server / Runtime Environments / Add / Red Hat JBoss Middleware / JBoss AS, Wildfy & EAP Server Tools
Wait for the install to complete.
Add / JBoss Community / WildFly 11 Runtime
Home direcetory : the previously unziped wildfly install



# Misc project info

Source encoding is UTF-8 (without BOM) with "CR LF" (windows) end of line caracters.


=====================
TODO
=====================
maven-javadoc-plugin show errors on build.
Don't use a custom assembly : Try to use the default one and exclude maven and eclipse stuff.
Add another module to add some recipes for building a "weekly bill of materials"
