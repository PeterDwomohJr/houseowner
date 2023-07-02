# Define the Shebang
#!/bin/bash


# Define Edge Kubernetes resources variables
EDGE_PVC="edge-pvc"
EDGE_PVC_MONGO_ZERO="edge-pvc-property-mongodb-0"
EDGE_PVC_MONGO_ONE="edge-pvc-property-mongodb-1"
EDGE_PVC_MONGO_TWO="edge-pvc-property-mongodb-2"
EDGE_MONGO_SECRETS="edge-mongodb-secret" 
EDGE_MONGO_CONFIG="edge-mongodb-config" 
EDGE_MONGO_STATEFULSET="edge-mongodb"
EDGE_MONGO_SERVICE="edge-mongodb-service" 
EDGE_CONFIG="edge-config"               
EDGE_DEPLOYMENT="edge"            
EDGE_SERVICE="edge-service" 


# Delete the Edge Kubernetes resources using the kubectl command
kubectl delete pvc $EDGE_PVC
kubectl delete pvc $EDGE_PVC_MONGO_ZERO
kubectl delete pvc $EDGE_PVC_MONGO_ONE
kubectl delete pvc $EDGE_PVC_MONGO_TWO
kubectl delete secret $EDGE_MONGO_SECRETS
kubectl delete configMap $EDGE_MONGO_CONFIG
kubectl delete statefulset $EDGE_MONGO_STATEFULSET
kubectl delete service $EDGE_MONGO_SERVICE
kubectl delete configMap $EDGE_CONFIG
kubectl delete deployment $EDGE_DEPLOYMENT
kubectl delete service $EDGE_SERVICE


# Verify if deleting the Edge Kubernetes resources was successful
if [ $? -eq 0 ]; then
  echo "Deleting the Edge Kubernetes resources successful."
else
  echo "Deleting the Edge Kubernetes resources failed."
fi