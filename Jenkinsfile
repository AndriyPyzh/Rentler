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
                - name: kaniko
                  image: gcr.io/kaniko-project/executor:debug
                  command:
                  - sleep
                  args:
                  - 9999999
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
        stage ('Exec Kaniko') {
            steps {
                sh "ls account-service/target"
                container('kaniko') {
                    sh "/kaniko/executor --dockerfile `pwd`/account-service/Dockerfile  --context `pwd`/account-service --destination gcr.io/rentler-370619/rentler_account-service"
                }
            }
        }
    }
}

