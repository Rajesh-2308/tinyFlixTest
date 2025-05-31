pipeline {
    agent any

    environment {
        // Customize if needed
        MAVEN_HOME = tool 'MAVEN_HOME' 
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', url: 'https://github.com/Rajesh-2308/tinyFlixTest.git'
            }
        }

        stage('Build and Test') {
            steps {
                bat "${MAVEN_HOME}/bin/mvn clean test"
            }
        }

        stage('Archive Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
                archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
            // Send email or Slack notification here if needed
        }
        failure {
            echo 'Build failed!'
            // Example: capture screenshot, send email, etc.
        }
    }
}
