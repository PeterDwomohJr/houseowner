# Define the Shebang
#!/bin/bash


# Set the Edge Kubernetes resources path and change to that directory
EDGE_KUBERNETES_RESOURCES_PATH="/home/kwadwo/houseowner/kubernetes/resources/edge"
cd $EDGE_KUBERNETES_RESOURCES_PATH


# Define Edge Kubernetes resources variables
EDGE_PVC="edge-pvc.yml"
EDGE_MONGO_SECRETS="edge-mongodb-secrets.yml" 
EDGE_MONGO_CONFIG="edge-mongodb-configmap.yml" 
EDGE_MONGO_STATEFULSET="edge-mongodb-statefulset.yml"
EDGE_MONGO_SERVICE="edge-mongodb-service.yml" 
EDGE_CONFIG="edge-configmap.yml"               
EDGE_DEPLOYMENT="edge-deployment.yml"            
EDGE_SERVICE="edge-service.yml" 


# Apply the Edge Kubernetes resources using the kubectl command
kubectl apply -f $EDGE_PVC
kubectl apply -f $EDGE_MONGO_SECRETS
kubectl apply -f $EDGE_MONGO_CONFIG
kubectl apply -f $EDGE_MONGO_STATEFULSET
kubectl apply -f $EDGE_MONGO_SERVICE
kubectl apply -f $EDGE_CONFIG
kubectl apply -f $EDGE_DEPLOYMENT
kubectl apply -f $EDGE_SERVICE


# Verify if applying the Edge Kubernetes resources was successful
if [ $? -eq 0 ]; then
  echo "Applying Edge Kubernetes resources successful."
else
  echo "Applying Edge Kubernetes resources failed."
fi