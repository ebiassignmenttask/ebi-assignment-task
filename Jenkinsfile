node{
	//enable below only if you are using windows 10 and using docker desktop, it may fail the image building 
   // environment {
    //   env.PATH = env.PATH + ";c:\\Windows\\System32"
   //}
   
	stage('Source Code Clone'){
    
		git credentialsId: 'GIT_CREDENTIALS', 
		branch: 'master', 
		url: 'https://github.com/ebiassignmenttask/ebi-assignment-task.git'
	}
	stage('Maven Clean Package'){
 
    	def mavenHome = tool name: "Maven_Local", type:"maven"
    	if(isUnix()){
    		sh "${mavenHome}/bin/mvn clean package"
    	}else{
    		bat "${mavenHome}/bin/mvn clean package"
    	}
	}
	stage('Build Docker image'){

		if(isUnix()){
 			sh 'docker build -t ebiassignmenttask/ebi-assignment-task .'
		}else{
			bat 'docker build -t ebiassignmenttask/ebi-assignment-task .'
		}
	}
	stage ('Push docker Image'){
    	withCredentials([string(credentialsId: 'DOCKER_HUB_CREDENTIALS', variable: 'DOCKER_HUB_CREDENTIALS')]) {
    		if(isUnix()){
        		sh "docker login -u devopstrika -p ${DOCKER_HUB_CREDENTIALS}"
        		sh "docker push ebiassignmenttask/ebi-assignment-task"
        	}else{
				bat "docker login -u devopstrika -p ${DOCKER_HUB_CREDENTIALS}"
        		bat "docker push ebiassignmenttask/ebi-assignment-task"
        	
        	}
    	}
	}
	stage('Deployment on K8s'){
		kubernetesDeploy(
			configs: 'deployment.yaml',
			kubeconfigId: 'KUBERNETES_CLUSTER',
			enableConfigSubstitution: true //only incase we have any environment variables
		)
	}
}