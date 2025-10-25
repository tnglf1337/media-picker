pipeline {
    agent any
    environment {
        PATH = "/usr/local/bin:/opt/homebrew/bin:${env.PATH}"
        DOCKER_CREDENTIALS = credentials('dockerhub-credentials')
    }
    stages {
        stage('Build frontend project...') {
            when {
                changeset "frontend/**"
            }

            steps {
                dir('frontend') {
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Build frontend Docker Image...') {
            when {
                changeset "frontend/**"
            }
            steps {
                dir('frontend') {
                    sh "docker login --username ${env.DOCKER_CREDENTIALS_USR} --password ${env.DOCKER_CREDENTIALS_PSW}"
                    sh 'docker build -t tneskedev/media-picker-frontend:latest .'
                    sh 'docker push tneskedev/media-picker-frontend:latest'
                }
            }
        }

        stage('Build backend project...') {
                    when {
                        changeset "backend/**"
                    }

                    steps {
                        dir('backend') {
                            sh 'npm install'
                            sh 'npm run build'
                        }
                    }
                }

                stage('Build backend Docker Image...') {
                    when {
                        changeset "backend/**"
                    }
                    steps {
                        dir('backend') {
                            sh "docker login --username ${env.DOCKER_CREDENTIALS_USR} --password ${env.DOCKER_CREDENTIALS_PSW}"
                            sh 'docker build -t tneskedev/media-picker-backend:latest .'
                            sh 'docker push tneskedev/media-picker-backend:latest'
                        }
                    }
                }
    }
}
