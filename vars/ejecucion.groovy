def call(){
    pipeline {
        agent any
	    
	parameters {
	    string(name: 'stage', defaultValue: '', description: 'Eleccion de stages') 
	} 

        stages {
            stage('Pipeline') {
                steps {
                   script {
						   
			figlet env.GIT_BRANCH
                        def tool = buildtool.get()
			   
			if (tool == 'gradle') {

				if(env.GIT_BRANCH =='develop')
				{
				println 'llamar a gradleci'	
				 //gradleci.call()
				} 
				if(env.GIT_BRANCH.contains('feature'))
				{
				println 'llamar a gradleci'
				 //gradleci.call()
				}
				if(env.GIT_BRANCH.contains('release'))
				{
				println 'llamar a gradlecd' 
				//gradlecd.call()
				}
			}
			
			if (tool == 'maven') {
				
				if(env.GIT_BRANCH =='develop')
				{
				println 'llamar a mavenci'		
				 //mavenci.call()
				} 
				if(env.GIT_BRANCH.contains('feature'))
				{
				println 'llamar a mavenci'
				 //mavenci.call()
				}
				if(env.GIT_BRANCH.contains('release'))
				{
				println 'llamar a mavencd'
				 //mavencd.call()
				}
			}
                   }  
                }
            }
        }
    }
}

return this;
