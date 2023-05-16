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
            label 'maven'
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
                - name: docker
                  image: docker:latest
                  command:
                    - cat
                  tty: true
                - name: docker-compose
                  image: docker/compose:alpine-1.29.2
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
        stage("Build image") {
    		steps {
        		container('docker-compose') {
        		    withCredentials([file(credentialsId: 'gcloud-creds', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        sh "docker-compose build $params.SERVICE"
                    }
                }
    		}
        }
        stage("Tag Image") {
            steps {
                container('docker') {
                    withCredentials([file(credentialsId: 'gcloud-creds', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        sh "docker tag rentler_$params.SERVICE gcr.io/rentler-370619/rentler_$params.SERVICE"
                    }
                }
            }
        }
        stage("Push Image") {
            steps {
                container('docker') {
                    withCredentials([file(credentialsId: 'gcloud-creds', variable: 'GOOGLE_APPLICATION_CREDENTIALS')]) {
                        sh "docker push gcr.io/rentler-370619/rentler_$params.SERVICE"
                    }
                }
            }
        }
    }
}

