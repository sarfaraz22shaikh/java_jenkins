pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/sarfaraz22shaikh/java_jenkins.git'
            }
        }
        stage('Compile') {
            steps {
                sh 'javac Hello.java'
            }
        }
        stage('Run') {
            steps {
                sh 'java Hello'
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t java-hello:latest .'
            }
        }
        stage('Run Container') {
            steps {
                sh 'docker run --rm --name java-hello-container java-hello:latest'
            }
        }
    }
}
