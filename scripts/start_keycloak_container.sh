# Define the Shebang
#!/bin/bash


# Define the path of the Keycloak docker-compose.yml resource
KEYCLOAK_DOCKER_COMPOSE_PATH="/home/kwadwo/houseowner/docker/keycloak"


# Change to the Keycloak docker-compose.yml rresource
cd $KEYCLOAK_DOCKER_COMPOSE_PATH


# Define the Keycloak docker-compose.yml
KEYCLOAK_DOCKER_COMPOSE="docker-compose-keycloak.yml"


# Apply the Keycloak docker-compose.yml resource
docker-compose -f $KEYCLOAK_DOCKER_COMPOSE up -d



# Verify if starting the Keycloak container was successful
if [ $? -eq 0 ]; then
  echo "Starting Keycloak container completed successfully."
else
  echo "Starting Keycloak container failed."
fi