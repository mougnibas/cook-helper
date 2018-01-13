# © Copyright 2017-2018 Yoann MOUGNIBAS
# 
# This file is part of Cook-Helper.
# 
# Cook-Helper is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# Cook-Helper is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with Cook-Helper. If not, see <http://www.gnu.org/licenses/>

#
# Cook Helper recipe dockerfile
#

# From a framework image
FROM mougnibas/base-framework:1.0.0

# Add all current files to the build context, at this location
COPY .  /opt/wildfly-swarm

# This location is the working directory
WORKDIR /opt/wildfly-swarm

# Health check here
# The healthcheck must be very quick(timeout 2s).
# If the check make more than 2 seconds, then it's failed.
# Default retries is 3
HEALTHCHECK --interval=10s --timeout=2s CMD curl --fail http://localhost:8080/healthcheck || exit 1

# Run the hollow wildfly-swarm with the application
# The weird parameters are for container restricted resources detection
CMD [ \
    "java", \
    "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", \
    "-Djava.net.preferIPv4Stack=true", \
    "-jar", \
    "web-profile.jar", \
    "cook-helper-recipe-web-${project.version}.war" ]