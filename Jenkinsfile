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
                    bat 'npm install'
                    bat 'npm run build'
                }
            }
        }

        stage('Build frontend Docker Image...') {
            when {
                changeset "frontend/**"
            }
            steps {
                dir('frontend') {
                    bat "docker login --username ${env.DOCKER_CREDENTIALS_USR} --password ${env.DOCKER_CREDENTIALS_PSW}"
                    bat 'docker build -t tneskedev/media-picker-frontend:latest .'
                    bat 'docker push tneskedev/media-picker-frontend:latest'
                }
            }
        }

        stage('Build backend project...') {
                    when {
                        changeset "backend/**"
                    }

                    steps {
                        dir('backend') {
                            bat 'npm install'
                            bat 'npm run build'
                        }
                    }
                }

                stage('Build backend Docker Image...') {
                    when {
                        changeset "backend/**"
                    }
                    steps {
                        dir('backend') {
                            bat "docker login --username ${env.DOCKER_CREDENTIALS_USR} --password ${env.DOCKER_CREDENTIALS_PSW}"
                            bat 'docker build -t tneskedev/media-picker-backend:latest .'
                            bat 'docker push tneskedev/media-picker-backend:latest'
                        }
                    }
                }
    }
}
