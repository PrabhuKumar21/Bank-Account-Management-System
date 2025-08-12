pipeline {
    agent any
     tools {
        jdk 'jdk17'
        maven 'maven3'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/PrabhuKumar21/Bank-Account-Management-System.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }
    }
}
