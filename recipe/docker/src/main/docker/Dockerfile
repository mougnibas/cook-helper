#
# Cook Helper recipe dockerfile
#

# From a slim jdk 8
FROM openjdk:8-jdk-slim

# Add all current files to the build context, at this location
COPY .  /opt/cook-helper-recipe

# This location is the working directory
WORKDIR /opt/cook-helper-recipe

# Health check here
# If the application don't respond, it is dead
# TODO
# HEALTHCHECK

# Run the hollow wildfly-swarm with the application
# The weird parameters are for container restricted resources detection
CMD [ \
    "java", \
    "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", \
    "-Djava.net.preferIPv4Stack=true", \
    "-jar", \
    "cook-helper-recipe-web-${project.version}-hollow-swarm.jar", \
    "cook-helper-recipe-web-${project.version}.war" ]