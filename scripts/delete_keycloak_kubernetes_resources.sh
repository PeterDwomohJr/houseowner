# Define the Shebang
#!/bin/bash


# Define Keycloak Kubernetes resources variables
KEYCLOAK_PVC="keycloak-pvc"
KEYCLOAK_PVC_MONGO_ZERO="edge-pvc-property-mongodb-0"
KEYCLOAK_PVC_MONGO_ONE="edge-pvc-property-mongodb-1"
KEYCLOAK_PVC_MONGO_TWO="edge-pvc-property-mongodb-2"
KEYCLOAK_MONGO_SECRET="keycloak-mongodb-secret" 
KEYCLOAK_MONGO_CONFIG="keycloak-mongodb-config" 
KEYCLOAK_MONGO_STATEFULSET="keycloak-mongodb"
KEYCLOAK_MONGO_SERVICE="keycloak-mongodb-service"
KEYCLOAK_SECRET="keycloak-secret" 
KEYCLOAK_CONFIG="keycloak-config"          
KEYCLOAK_DEPLOYMENT="keycloak" 
KEYCLOAK_SERVICE="keycloak-service"           


# Delete the Keycloak Kubernetes resources using the kubectl command
kubectl delete pvc $KEYCLOAK_PVC
kubectl delete pvc $KEYCLOAK_PVC_MONGO_ZERO
kubectl delete pvc $KEYCLOAK_PVC_MONGO_ONE
kubectl delete pvc $KEYCLOAK_PVC_MONGO_TWO
kubectl delete secret $KEYCLOAK_MONGO_SECRET
kubectl delete configMap $KEYCLOAK_MONGO_CONFIG
kubectl delete statefulset $KEYCLOAK_MONGO_STATEFULSET
kubectl delete service $KEYCLOAK_MONGO_SERVICE
kubectl delete secret $KEYCLOAK_SECRET
kubectl delete configMap $KEYCLOAK_CONFIG
kubectl delete deployment $KEYCLOAK_DEPLOYMENT
kubectl delete service $KEYCLOAK_SERVICE


# Verify if deleting the Keycloak Kubernetes resources was successful
if [ $? -eq 0 ]; then
  echo "Deleting Keycloak Kubernetes resources successful."
else
  echo "Deleting Keycloak Kubernetes resources failed."
fi