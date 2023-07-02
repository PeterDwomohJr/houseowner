# Define the Shebang
#!/bin/bash


# Set the Keycloak Kubernetes resources path and change to that directory
KEYCLOAK_KUBERNETES_RESOURCES_PATH="/home/kwadwo/houseowner/kubernetes/resources/keycloak"
cd $KEYCLOAK_KUBERNETES_RESOURCES_PATH


# Define Keycloak Kubernetes resources variables
KEYCLOAK_PVC="keycloak-pvc.yml"
KEYCLOAK_MONGO_SECRET="keycloak-mongodb-secrets.yml" 
KEYCLOAK_MONGO_CONFIG="keycloak-mongodb-configmap.yml" 
KEYCLOAK_MONGO_STATEFULSET="keycloak-mongodb-statefulset.yml"
KEYCLOAK_MONGO_SERVICE="keycloak-mongodb-service.yml"
KEYCLOAK_SECRET="keycloak-secret.yml" 
KEYCLOAK_CONFIG="keycloak-configmap.yml"          
KEYCLOAK_DEPLOYMENT="keycloak-deployment.yml" 
KEYCLOAK_SERVICE="keycloak-service.yml"           


# Apply the Keycloak Kubernetes resources using the kubectl command
kubectl apply -f $KEYCLOAK_PVC
kubectl apply -f $KEYCLOAK_MONGO_SECRET
kubectl apply -f $KEYCLOAK_MONGO_CONFIG
kubectl apply -f $KEYCLOAK_MONGO_STATEFULSET
kubectl apply -f $KEYCLOAK_MONGO_SERVICE
kubectl apply -f $KEYCLOAK_SECRET
kubectl apply -f $KEYCLOAK_CONFIG
kubectl apply -f $KEYCLOAK_DEPLOYMENT
kubectl apply -f $KEYCLOAK_SERVICE


# Verify if applying the Keycloak Kubernetes resources was successful
if [ $? -eq 0 ]; then
  echo "Applying Keycloak Kubernetes resources successful."
else
  echo "Applying Keycloak Kubernetes resources failed."
fi