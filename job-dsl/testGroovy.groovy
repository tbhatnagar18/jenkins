job('Node JS Example'){
	scm {
        remote {
            github('https://github.com/wardviaene/docker-demo.git')
        }
        extensions {
            userIdentity {
				name('DSL User')
				email('jenkins-dsl@newtech.academy')
			}
        }
    }
	triggers{
		scm('H/5 * * * *') 
	}
	steps{
		shell('npm install')
	}
}