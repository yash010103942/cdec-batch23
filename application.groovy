pipeline {
    agent any

    stages {

        stage('PULL') {
            steps {
               git 'https://github.com/yash010103942/cdec-b23-infra.git'
            }
        }

        stage('FRONTEND-DOCKER-BUILD') {
            steps {
                sh '''
                cd frontend
                docker build -t yashkapse01/easy-frontend:latest .
                '''
            }
        }

        stage('BACKEND-DOCKER-BUILD') {
            steps {
                sh '''
                cd backend
                docker build -t yashkapse01/easy-backend:latest .
                '''
            }
        }

        stage('DOCKER-PUSH') {
            steps {
                sh '''
                docker push yashkapse01/easy-frontend:latest
                docker push yashkapse01/easy-backend:latest
                '''
            }
	}
            stage('DOCKER-CLEAN') {
            steps {
                sh '''
                docker rmi -f yashkapse01/easy-frontend:latest
                docker rmi  -f yashkpase01/easy-backend:latest
                '''
            }
        }

        stage('DEPLOY') {
            steps {
                sh 'kubectl apply -f simple-deploy/'
            }
        }
    }
}
