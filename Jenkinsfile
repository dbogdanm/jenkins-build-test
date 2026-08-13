//vrem ca pipelinul sa faca astea:
//1. sa faca din cod artifact(prin maven 3.9)
//2. din artifact sa faca dockerimage (prin build)
//3. acel dockerimage sa fie pushat pe nexus

pipeline
{
    agent any

    environment
    {
        ADRESA_IP_NEXUS = credentials('ADRESA_NEXUS')
        PORT_NEXUS = credentials('PORT_NEXUS')
        NEXUS_CREDS = credentials('nexus')

    }

    tools
    {
        maven 'maven-3.9'
    }

    stages
    {

        stage('test_maven')
        {
            steps
            {
                sh 'mvn --version'
            }
        }


        stage('test')
        {
            steps
            {
                sh 'mvn test' //testele se executa automat, daca dau eroare, pipelinul se opreste instant aici
            }
        }

        stage('package')
        {
            steps
            {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('build')
        {
            steps
            {
                sh "docker build -t $ADRESA_IP_NEXUS:$PORT_NEXUS/java_app:$BUILD_NUMBER . "
            }
        }


        stage('login_si_push')
        {
            steps
            {
                sh "docker login --username $NEXUS_CREDS_USR -p $NEXUS_CREDS_PSW $ADRESA_IP_NEXUS:$PORT_NEXUS"
                sh "docker push $ADRESA_IP_NEXUS:$PORT_NEXUS/java_app:$BUILD_NUMBER"
                sh 'docker rmi $(docker images --filter=reference="$ADRESA_IP_NEXUS:$PORT_NEXUS/java_app*" -q)'
                sh "docker logout $ADRESA_IP_NEXUS:$PORT_NEXUS"
            }
        }


    }



}