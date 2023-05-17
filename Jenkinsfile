pipeline{
    parameters {
        choice(name: "SERVICE",
               choices: [
                    "rentler-helper",
                    "account-service",
                    "apartment-service",
                    "notification-service",
                    "auth-service",
                    "api-gateway"
                    ],
               description: "service")
    }
    agent {
	    kubernetes {
            cloud 'kubernetes'
            yaml '''
            apiVersion: v1
            kind: Pod
            spec:
              containers:
                - name: maven
                  image: maven:3.8.1-openjdk-15
                  command:
                    - cat
                  tty: true
            '''
	    }
	}
    stages{
        stage("Build") {
    		steps {
        		container('maven') {
        		    withCredentials([file(credentialsId: 'gcloud-creds', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        sh "mvn clean package -DskipTests -Pdev -f $params.SERVICE/pom.xml"
                    }
                }
    		}
        }
        stage("Test") {
    		steps {
        		container('maven') {
        		    withCredentials([file(credentialsId: 'gcloud-creds', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        sh "mvn verify -Pdev -f $params.SERVICE/pom.xml"
        		    }
                }
    		}
        }
        stage("Install") {
    		steps {
        		container('maven') {
        		    withCredentials([file(credentialsId: 'gcloud-creds', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        sh "mvn deploy -DskipTests -Pdev -f $params.SERVICE/pom.xml"
                    }
                }
    		}
        }
    }
}

