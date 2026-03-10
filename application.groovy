pipeline {
    agent any

    environment {
        DOCKER_USER = "yashkapse01"
    }

    stages {

        stage('Clone Code') {
            steps {
                git 'https://github.com/chetanraval07/jenkins-final26.git'
            }
        }

        stage('Build Images') {
            steps {
                sh 'docker build -t yashkapse01/frontend ./frontend'
                sh 'docker build -t yashkapse01/backend ./backend'
            }
        }

        stage('Push Images') {
            steps {
                sh 'docker push yashkapse01/frontend'
                sh 'docker push yashkapse01/backend'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/'
            }
        }

    }
}
