pipeline {

    agent {
        label 'master' //The id of the slave/agent where the build should be executed, if it doesn't matter use "agent any" instead.
    }

    triggers {
        cron('H 0-23/1 * * *') //regular builds
    }

    stages {

        stage('Cloning with Script') {
            steps {
                script {

                       git credentialsId: 'c48e701b-4e1c-4175-9edf-54b3c6e450a9', url: 'https://github.com/StryhelskiAndrei/SwaggerPetStoreAllure.git'

                }
            }
        }
        stage('Tests') {
            steps {
                script {

                        sh $CLEAN_TEST //run a gradle task

                }
            }
        }
        stage('Allure reports') {
                    steps {
                        script {
                            allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: './allure-results']]
                            ])
                            }
                        }
                        }
}
}
