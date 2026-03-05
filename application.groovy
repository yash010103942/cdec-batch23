pipeline {
agent any

```
stages {

    stage('PULL') {
        steps {
            git 'https://github.com/chetanraval07/jenkins-final26.git'
        }
    }

    stage('FRONTEND-DOCKER-BUILD') {
        steps {
            sh '''
            cd frontend
            docker build -t shivansh7310/easy-frontend:latest .
            '''
        }
    }

    stage('BACKEND-DOCKER-BUILD') {
        steps {
            sh '''
            cd backend
            docker build -t shivansh7310/easy-backend:latest .
            '''
        }
    }

    stage('DOCKER-PUSH') {
        steps {
            sh '''
            docker push shivansh7310/easy-frontend:latest
            docker push shivansh7310/easy-backend:latest
            '''
        }
    }

    stage('DOCKER-CLEAN') {
        steps {
            sh '''
            docker rmi -f shivansh7310/easy-frontend:latest
            docker rmi -f shivansh7310/easy-backend:latest
            '''
        }
    }

    stage('DEPLOY') {
        steps {
            sh 'kubectl apply -f simple-deploy/'
        }
    }

}
```

}
