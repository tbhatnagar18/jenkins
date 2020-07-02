job('d2-refresh-mercer-SOT-MEVWTAPSONATA47-jobs'){
	parameters {
		activeChoiceParam('dbversion') 
			{
            choiceType('SINGLE_SELECT')
            groovyScript 
				{
                script('DBVersion_Get.groovy')
			}
		}	
		stringParam('client', 'Mercer', null)
    }
    scm{
		svn{
			location('http://svn.bravurasolutions.com/svn/artefacts/Sonata/trunk/ant/ahp/config'){
				credentials('23091701-1e6b-4e6f-9434-e781fa901339')
				depth(SvnDepth.INFINITY)
				ignoreExternals(true)
			}
		}
	}
    configure {
		project ->
		project / allowedNodes('MEVWTAPSONATA47')
    }
    steps{
		shell('set JAVA_HOME=C:\\Program Files\\Java\\jdk1.8.0_181 cd "C:\\Applications\\Jenkins\\" java autosave C:\\Applications\\Jenkins\\agent\\workspace\\APAC-SUPPORT\\MERCER\\MCSOT_810\\d2-refresh-mercer-SOT-MEVWTAPSONATA47-jobs\\config\\MCSOT_810\\profile.properties "data.refresh.dir=C\\:\\\\Users\\\\pkveggalam\\\\Desktop\\\\SOT\\\\MCSOT_8.10.201.3492.2_01.06.2020" "data.refresh.dir=C\\:\\\\MCSOT_810\\\\SOT\\\\R10\\\\MCSOT_8.10.201.2357_06.10.2019 %dbversion%" cd C:\\Applications\\Jenkins\\agent\\workspace\\APAC-SUPPORT\\MERCER\\MCSOT_810\\d2-refresh-mercer-SOT-MEVWTAPSONATA47-jobs wget http://update/8.10-Release/bravura-installer.jar java -jar bravura-installer.jar action=prepare.data.refresh -Dversion.to=%dbversion% -Dclient.from=Aucr -Dclient.to=Aucr -Dinstaller.updates.dir=C:\\Users\\meljautodeploy\\bravurasolutions\\updates_sonata config.dir=C:\\Applications\\Jenkins\\agent\\workspace\\APAC-SUPPORT\\MERCER\\MCSOT_810\\d2-refresh-mercer-SOT-MEVWTAPSONATA47-jobs\\config profile=MCSOT_810 REM java -jar bravura-installer.jar action=execute.data.refresh -Dversion.to=%dbversion% -Dclient.from=Aucr -Dclient.to=Aucr -Dinstaller.updates.dir=C:\\Users\\ggnautodeploy\\bravurasolutions\\updates_sonata config.dir=C:\\Applications\\Jenkins\\agent\\workspace\\APAC-SUPPORT\\MCSOT_810_jobs\\d2-refresh-mercer-SOT-MEVWTAPSONATA47-jobs\\config profile=MCSOT_810')
	}
    publishers{
		extendedEmail {
			recipientList('cscd@bravurasolutions.com')
			triggers {
				failure{
					subject('$PROJECT_DEFAULT_SUBJECT')
					content('$PROJECT_DEFAULT_CONTENT')
					replyToList('$PROJECT_DEFAULT_REPLYTO')
					contentType('project')
				}
			}
			contentType('default')
			defaultSubject('$DEFAULT_SUBJECT')
			defaultContent('$DEFAULT_CONTENT')
			preSendScript('$DEFAULT_PRESEND_SCRIPT')
			replyToList('$DEFAULT_REPLYTO')
		}
	}
    configure { 
        it / wrappers <<  'org.jvnet.hudson.tools.versionnumber.VersionNumberBuilder' {
            versionNumberString('${dbversion}')
            projectStartDate('2017-09-12 14:00:00.0 UTC')
            environmentVariableName('environmentVariableName')
            worstResultForIncrement('NOT_BUILT')
            skipFailedBuilds(false)
            useAsBuildDisplayName(true)
        }
	}
}