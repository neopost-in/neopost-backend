# Neopost - Backend

### Reference Documentation
* Running locally from source code -
    * Clone the source code.
    * Configure maven and sync dependencies.
    * Run the application.
    * The application is documented using OpenAPI v3, which can be accessible at [hostname]/swagger-ui.html
* Running using Docker and deploy to a Kubernetes cluster -
  * Clone the source code.
  * Build the Docker image using the dockerfile - "Dockerfile-Backend".
  * Apply the Kubernetes deployment and service from infra/k8s/ceaa-backend-depl.yaml
* Running from pre-compiled Docker image -
  * Pull the pre-compiled Docker image from Dockerhub - "anshumanc6/ceaa-backend".
  * Deploy it to a Kubernetes cluster or run locally using Docker.


####Notes
* This application uses h2 in-memory database.
* All the posts are stored in posts.json file which is present in resources/json folder.
* Upon start-up, all posts get stored to the database.