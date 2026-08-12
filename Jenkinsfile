pipeline
{
    agent any

    tools
    {
        maven 'maven-3.9'
    }
    environment {
    NEXUS = credentials('nexus')
    }

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
                sh 'docker build -t 100.119.85.118:8082/java_app:1.3 .'
            }
        }
        stage("push")
        {
            steps
            {
                sh  '''
                      echo $NEXUS_PSW | docker login -u $NEXUS_USR --password-stdin 100.119.85.118:8082
                      docker push 100.119.85.118:8082/java_app:1.3
                    '''
            }
        }
    }
}