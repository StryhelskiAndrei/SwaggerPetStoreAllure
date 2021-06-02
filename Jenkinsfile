pipeline {

    agent {
        label 'master' //The id of the slave/agent where the build should be executed, if it doesn't matter use "agent any" instead.
    }

    triggers {
        cron('H */8 * * *') //regular builds
    }

    stages {

        stage('Unit & Integration Tests') {
            steps {
                script {

                        sh './gradlew clean test --no-daemon' //run a gradle task

                }
            }
        }
        stage('allure reports') {
                    steps {
                        script {
                            allure([
                                includeProperties: false,
                                jdk: '',
                                properties: [],
                                reportBuildPolicy: 'ALWAYS',
                                results: [[path: 'target/allure-results']]
                            ])
                            }
                        }
                        }
}
}
