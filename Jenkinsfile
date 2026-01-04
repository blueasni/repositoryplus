pipeline {
    agent any

    environment {
        // Replace with your Docker Hub username and repository name
        DOCKER_IMAGE = "blueasni/repositoryplus"
        DOCKER_HUB_CREDS = credentials('docker_secret')
    }

    stages {
        stage('Checkout') {
        steps {
                checkout scm
            }
        }

        stage('Maven Build') {
            steps {
              sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                script {
                    sh "docker build -t ${DOCKER_IMAGE}:${env.BUILD_ID} ."
                    sh "docker tag ${DOCKER_IMAGE}:${env.BUILD_ID} ${DOCKER_IMAGE}:latest"
                }
            }
        }

        stage('Docker Push') {
            steps {
                script {
                    sh "echo ${DOCKER_HUB_CREDS_PSW} | docker login -u ${DOCKER_HUB_CREDS_USR} --password-stdin"
                    sh "docker push ${DOCKER_IMAGE}:${env.BUILD_ID}"
                    sh "docker push ${DOCKER_IMAGE}:latest"
                }
            }
        }
    }    post {
        always {
            sh "docker logout"
            cleanWs()
        }
    }}