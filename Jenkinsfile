//vrem ca pipelinul sa faca astea:
//1. sa faca din cod artifact(prin maven 3.9)
//2. din artifact sa faca dockerimage (prin build)
//3. acel dockerimage sa fie pushat pe nexus

def gv


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

        stage('init')
        {
            steps
            {
                script
                {
                    gv = load 'script.groovy'
                }
            }
        }

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


        stage('pushCheck')
        {
            input
            {
                message "dai push in nexus la aceasta imagine?"
                ok "Da"
                cancel "Nu"

            }
        }


        stage('login_si_push')
        {
            when
            {
                branch 'master'
            }

            steps
            {
                script
                {
                    gv.loginPush() //apel invelit in script
                }
            }

            post
            {
                success
                {
                    script
                    {
                        gv.cleanupSignoff() //la fel si aici
                    }
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