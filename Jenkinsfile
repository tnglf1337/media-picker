pipeline {
    agent any
    environment {
        PATH = "/usr/local/bin:/opt/homebrew/bin:${env.PATH}"
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')
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
