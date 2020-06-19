job('Node JS Example'){
	scm {
        github('https://github.com/wardviaene/docker-demo.git')
    }
	triggers{
		scm('H/5 * * * *') 
	}
	steps{
		shell('npm install')
	}
}