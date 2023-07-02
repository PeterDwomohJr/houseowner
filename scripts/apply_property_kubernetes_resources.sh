# Define the Shebang
#!/bin/bash


# Set the Property Kubernetes resources path and change to that directory
PROPERTY_KUBERNETES_RESOURCES_PATH="/home/kwadwo/houseowner/kubernetes/resources/property"
cd $PROPERTY_KUBERNETES_RESOURCES_PATH


# Define Property Kubernetes resources variables
PROPERTY_PVC="property-pvc.yml"  
PROPERTY_MONGO_SECRET="property-mongodb-secret.yml"  
PROPERTY_MONGO_CONFIG="property-mongodb-config.yml"
PROPERTY_MONGO_STATEFULSET="property-mongodb-statefulset.yml" 
PROPERTY_MONGO_SERVICE="property-mongodb-service.yml"
PROPERTY_CONFIG="property-config.yml"          
PROPERTY_DEPLOYMENT="property-deployment.yml"         
PROPERTY_SERVICE="property-service.yml"


# Apply the Kubernetes resources using the kubectl command
kubectl apply -f $PROPERTY_PVC
kubectl apply -f $PROPERTY_MONGO_SECRET
kubectl apply -f $PROPERTY_MONGO_CONFIG
kubectl apply -f $PROPERTY_MONGO_STATEFULSET
kubectl apply -f $PROPERTY_MONGO_SERVICE
kubectl apply -f $PROPERTY_CONFIG
kubectl apply -f $PROPERTY_DEPLOYMENT
kubectl apply -f $PROPERTY_SERVICE


# Verify if applying the Kubernetes resources was successful
if [ $? -eq 0 ]; then
  echo "Applying Kubernetes resources successful."
else
  echo "Applying Kubernetes resources failed."
fi