String basePath = 'Tushar_client'
String env = 'TBtrunk'
String node = 'guvwdaptrunk02'
String repo = 'tbhatnagar18/jenkins'

folder('$basePath'){
	description = 'folder containing jobs for $basePath client'
}

job('$basePath/a0_$env-install-and-db-upgrade') {
    scm {
        github 'tbhatnagar18/jenkins'
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        shell('echo %VersionTo% >c:/applications/jenkins/DBVersion.txt')
		shell('echo %ModuleVersion% >c:/applications/jenkins/WebVersion.txt')
		shell('echo %Installer_Version% >C:/applications/jenkins/InstallerVersion.txt')
    }
}

job('$basePath/STOP-ONLY-$env-$node') {
    parameters {
        stringParam 'host'
    }
    steps {
        shell("cd 'C:/applications/jenkins/'	call stop_vstrunk_8080.lnk for /f \"delims=\" %%J in (\'java CheckPort 8080\') do ( set portuse=%%J) if %portuse%==portinuse (netstat -aop TCP|find \"8080\" >port.txt type NUL >forcekill.bat for /f %%X in (\'java CheckPort 8080 C:\\applications\\jenkins\' ) do ( @echo taskkill /f /PID %%X >>forcekill.bat ) @echo exit /B %errorlevel% >>forcekill.bat	call forcekill.bat )")
    }
}