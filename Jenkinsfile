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
                bat 'javac Hello.java'
            }
        }
        stage('Run') {
            steps {
                bat 'java Hello'
            }
        }
    }
}
