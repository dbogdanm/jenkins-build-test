def loginPush()
{
    sh 'echo "$NEXUS_CREDS_PSW" | docker login -u "$NEXUS_CREDS_USR" --password-stdin $ADRESA_IP_NEXUS:$PORT_NEXUS'
    sh 'docker push $ADRESA_IP_NEXUS:$PORT_NEXUS/java_app:$BUILD_NUMBER'
}


def  cleanupSignoff()
{
    echo 'delogare si cleanup'
    sh 'docker rmi $ADRESA_IP_NEXUS:$PORT_NEXUS/java_app:$BUILD_NUMBER'
}

return this