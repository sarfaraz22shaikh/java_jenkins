pipeline {
    agent any

    stages {

        stage('Repo Cloning') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/sarfaraz22shaikh/java_jenkins.git'
            }
        }

        stage('Building Docker Image') {
            steps {
                sh 'docker build -t java-hello-world .'
            }
        }

        stage('Tagging and Pushing to DockerHub') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-cred',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_TOKEN'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_TOKEN" | docker login \
                        -u "$DOCKER_USER" --password-stdin

                        docker tag java-hello-world \
                        "$DOCKER_USER/java-hello-world:latest"

                        docker push \
                        "$DOCKER_USER/java-hello-world:latest"

                        docker logout
                    '''
                }
            }
        }

        stage('Verify DockerHub Image') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-cred',
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_TOKEN'
                    )
                ]) {
                    sh '''
                        docker pull "$DOCKER_USER/java-hello-world:latest"
                        docker images
                    '''
                }
            }
        }
    }
}
