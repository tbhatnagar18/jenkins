10.times{
job('Tushar/Node JS Example' +it){
	scm {
        github('wardviaene/docker-demo')
    }
	steps{
		shell('npm install')
	}
}
}