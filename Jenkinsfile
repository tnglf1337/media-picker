pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS = credentials('docker-credentials')
    }

    stages {
        stage('Build project...') {
            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build Docker Image...') {
            steps {
                dir('frontend') {
                    sh "docker login --username ${env.DOCKER_CREDENTIALS_USR} --password ${env.DOCKER_CREDENTIALS_PSW}"
                    sh 'docker build -t tneskedev/media-picker:latest .'
                    sh 'docker push tneskedev/media-picker:latest'
                }
            }
        }
    }
}
