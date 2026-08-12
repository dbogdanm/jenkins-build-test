pipeline {
    agent any
    stages {
        stage("build") {
            steps { sh 'hostname; whoami; pwd' }
        }
        stage("test") {
            steps { echo "testez" }
        }
        stage("deploy") {
            steps { echo "deployez" }
        }
    }
}