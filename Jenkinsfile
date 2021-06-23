pipeline {

    agent {
        label 'master' //The id of the slave/agent where the build should be executed, if it doesn't matter use "agent any" instead.
    }
    parameters {
        string(name: 'TEST', defaultValue: './gradlew clean test --no-daemon', description: 'This parameter run suits')
      }

    //parameters { choice(name: 'CHOICES', choices: ['./gradlew test -PmasterSuite', './gradlew test -PpetSuite', './gradlew test -PorderSuite', './gradlew test -PuserSuite'], description: 'Choose the suite') }

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

                       // sh './gradlew clean test --no-daemon' //run a gradle task
                        //sh $TEST
                        sh 'rm -rf ./allure-results'
                        //sh '$CHOICES' //run a gradle task
                         sh '$TEST' //run a gradle task
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
