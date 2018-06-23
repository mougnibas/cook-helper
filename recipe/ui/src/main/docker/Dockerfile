# (c) Copyright 2017-2018 Yoann MOUGNIBAS
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

# From a base java image
FROM openjdk:10

# Add all current files to the build context, at this location
COPY .  /opt/jboss/wildfly-swarm/

# Set workdir to wildfly swarm installation directory
WORKDIR /opt/jboss/wildfly-swarm/

# Health check here
# The healthcheck must be very quick(timeout 2s).
# If the check make more than 2 seconds, then it's failed.
# Default retries is 3
HEALTHCHECK --interval=10s --timeout=2s CMD curl --fail http://localhost:8090/health || exit 1

# Run the server
CMD [ \
	"java", \
	"-jar", "wildfly-swarm-web.jar", "cook-helper-recipe-ui.war", \
	"-Djava.net.preferIPv4Stack=true",\
	"-Dswarm.port.offset=10" \
]