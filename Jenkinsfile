pipeline {
     tools{
        jdk 'Java8'
        maven 'Maven_Local'
    }
    agent any 
    stages { 
        stage('checkout'){
            steps{
               git branch: 'master', url: 'https://github.com/devopstrika/jenkins-pipeline-docker.git'
            }
        }
        stage('compile'){
            
            steps{
                bat 'mvn compile'
            }
        
        }
        stage('test'){
           
            steps{
            bat 'mvn test'
            }
        }
        
        stage('package'){
           
            steps{
            bat 'mvn package'
            }
        }
        
    }
}