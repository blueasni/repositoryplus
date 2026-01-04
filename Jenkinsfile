pipeline {
    agent any

    environment {
        // Define variables for reusability
        DOCKER_IMAGE = "my-app-name"
        DOCKER_TAG = "${env.BUILD_NUMBER}"
        REGISTRY_USER = "blueasni"
    }

    stages {
        stage('Checkout') {
            steps {
                // Pulls code from your configured repository
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
                // Runs unit tests and packages the JAR file
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Builds the image using the Dockerfile in your root directory
                    dockerImage = docker.build("${REGISTRY_USER}/${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push to Registry') {
            steps {
                script {
                    // 'docker-hub-credentials' is the ID of your credentials in Jenkins
                    docker.withRegistry('', 'docker-hub-credentials') {
                        dockerImage.push()
                        dockerImage.push("latest")
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up the workspace and local docker images to save space
            sh "docker rmi ${REGISTRY_USER}/${DOCKER_IMAGE}:${DOCKER_TAG} || true"
        }
    }
}