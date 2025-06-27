pipeline {
    agent any
    
    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from GitHub
                git url: 'https://github.com/your-username/your-repository.git', branch: 'main'
            }
        }
        
        stage('Build') {
            steps {
                // Simulate building the project
                echo 'Building the project...'
                sh 'echo Build successful'
            }
        }
        
        stage('Test') {
            steps {
                // Simulate testing the project
                echo 'Running tests...'
                sh 'echo Tests passed!'
            }
        }
    }

    post {
        success {
            echo 'Build succeeded'
        }
        failure {
            echo 'Build failed'
        }
    }
}
