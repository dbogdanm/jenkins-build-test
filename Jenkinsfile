pipeline
{
    agent any
    stages
    {
        stage("build")
        {
            steps
            {
                sh 'mvn clean package'
            }
        }
        stage("docker build")
        {
            steps
            {
                sh 'docker build -t 100.119.85.118:8082java_app:1.3 .'
            }
        }
        stage("push")
        {
            steps
            {
                echo "evident ca nu merge"
            }
        }
    }
}