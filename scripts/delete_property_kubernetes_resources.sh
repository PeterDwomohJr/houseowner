# Define the Shebang
#!/bin/bash


# Define Property Kubernetes resources variables
PROPERTY_PVC="property-pvc"  
PROPERTY_PVC_MONGO_ZERO="property-pvc-property-mongodb-0"
PROPERTY_PVC_MONGO_ONE="property-pvc-property-mongodb-1"
PROPERTY_PVC_MONGO_TWO="property-pvc-property-mongodb-2"
PROPERTY_MONGO_SECRET="property-mongodb-secret"  
PROPERTY_MONGO_CONFIG="property-mongodb-config"
PROPERTY_MONGO_STATEFULSET="property-mongodb" 
PROPERTY_MONGO_SERVICE="property-mongodb-service"
PROPERTY_CONFIG="property-config"          
PROPERTY_DEPLOYMENT="property"         
PROPERTY_SERVICE="property-service"


# Delete the Property Kubernetes resources using the kubectl command
kubectl delete pvc $PROPERTY_PVC
kubectl delete pvc $PROPERTY_PVC_MONGO_ZERO
kubectl delete pvc $PROPERTY_PVC_MONGO_ONE
kubectl delete pvc $PROPERTY_PVC_MONGO_TWO
kubectl delete secret $PROPERTY_MONGO_SECRET
kubectl delete configMap $PROPERTY_MONGO_CONFIG
kubectl delete statefulset $PROPERTY_MONGO_STATEFULSET
kubectl delete service $PROPERTY_MONGO_SERVICE
kubectl delete configMap $PROPERTY_CONFIG
kubectl delete deployment $PROPERTY_DEPLOYMENT
kubectl delete service $PROPERTY_SERVICE

# Verify if deleting the Property Kubernetes resources was successful
if [ $? -eq 0 ]; then
  echo "Deleting Property Kubernetes resources successful."
else
  echo "Deleting Property Kubernetes resources failed."
fi