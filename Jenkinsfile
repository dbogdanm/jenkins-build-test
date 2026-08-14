//vrem ca pipelinul sa faca astea:
//1. sa faca din cod artifact(prin maven 3.9)
//2. din artifact sa faca dockerimage (prin build)
//3. acel dockerimage sa fie pushat pe nexus

pipeline
{
    agent any

    parameters
    {
        booleanParam(name: 'PUSH', defaultValue: true, description: '1 = push, 0 = nu' )
    }

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
            when
            {
                branch 'master'
            }

            steps
            {
                sh 'docker build -t $ADRESA_IP_NEXUS:$PORT_NEXUS/java_app:$BUILD_NUMBER . '
            }
        }


        stage('login_si_push')
        {
            when
            {
                allOf
                {
                    branch 'master'  //verificare jenkins, evaluata de jenkins, deci stie direct ce sa ii faca
                    expression { params.PUSH == true } //ce e in acolade e groovy, de aia se pune expression ca sa aiba cum jenkins sa evalueeze
                }

            }

            steps
            {
                sh 'echo "$NEXUS_CREDS_PSW" | docker login -u "$NEXUS_CREDS_USR" --password-stdin $ADRESA_IP_NEXUS:$PORT_NEXUS'
                sh 'docker push $ADRESA_IP_NEXUS:$PORT_NEXUS/java_app:$BUILD_NUMBER'
            }

            post
            {
                success
                {
                    echo 'delogare si cleanup'
                    sh 'docker rmi $ADRESA_IP_NEXUS:$PORT_NEXUS/java_app:$BUILD_NUMBER'

                }
            }

        }


    }

    post
    {
        always
        {
                sh 'docker logout $ADRESA_IP_NEXUS:$PORT_NEXUS'
        }

    }

}