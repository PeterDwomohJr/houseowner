#!/bin/bash

# Variables
DOCKER_IMAGE_NAME="property"
DOCKER_IMAGE_TAG="0.0.0"
DOCKER_IMAGE_REPOSITORY_URL="docker.io"
DOCKER_USERNAME="theblackengineer"
DOCKERFILE_PATH="/home/kwadwo/houseowner/property"

# Build the Docker image
docker build -t $DOCKER_USERNAME/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG $DOCKERFILE_PATH

# Authenticate. If you are not unauthenticated, you will be asked for usernane and password
docker login 

# Tag the Docker image
docker tag $DOCKER_USERNAME/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG $DOCKER_USERNAME/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

# Push the Docker image to the registry
docker push $DOCKER_IMAGE_REPOSITORY_URL/$DOCKER_USERNAME/$DOCKER_IMAGE_NAME:$DOCKER_IMAGE_TAG

# verify if the build and push was successful
if [ $? -eq 0 ]; then
  echo "Build and Push to docker.io completed successfully."
else
  echo "Build and Push to docker.io failed."
fi