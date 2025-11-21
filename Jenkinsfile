pipeline {
    agent any
    environment {
        PATH = "/usr/local/bin:/opt/homebrew/bin:${env.PATH}"
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')
    }

    stages {

        stage('Build frontend project...') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build frontend Docker Image...') {
            steps {
                dir('frontend') {
                    sh "docker login --username ${env.DOCKER_CREDENTIALS_USR} --password ${env.DOCKER_CREDENTIALS_PSW}"
                    sh 'docker build -t tneskedev/mediaverse-frontend:latest .'
                    sh 'docker push tneskedev/mediaverse-frontend:latest'
                }
            }
        }

        stage('Build backend Docker Image...') {
            steps {
                dir('backend') {
                    sh "docker login --username ${env.DOCKER_CREDENTIALS_USR} --password ${env.DOCKER_CREDENTIALS_PSW}"
                    sh 'docker build -t tneskedev/mediaverse-backend:latest .'
                    sh 'docker push tneskedev/mediaverse-backend:latest'
                }
            }
        }
    }
}
