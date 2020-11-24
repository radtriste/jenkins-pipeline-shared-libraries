package org.kie.jenkins

def class GitPRHelper {

    def steps    

    String directory = ''
    
    String mainCredsId = ''
    String mainBranch = ''
    
    String botCredsId = ''

    GitPRHelper(def steps, String project){
        this.steps = steps
    }

    GitPRHelper inDirectory(String directory){
        this.directory = directory
        return this
    }

    GitPRHelper onBranch(String branch){
        this.directory = directory
        return this
    }

    GitPRHelper withMainCredentialsId(String mainCredsId){
        this.mainCredsId = mainCredsId
        return this
    }

    GitPRHelper withBotCredentialsId(String botCredsId){
        this.botCredsId = botCredsId
        return this
    }

    def prepareForPR(){
        assert this.botCredsId : 'No bot credentials id has been given'
        executeInDirIfExist {
            githubscm.
            githubscm.forkRepo(botCredsId)
        }
    }

    private void executeInDirIfExist(Closure action){
        if(this.directory){
            dir(this.directory) {
                action()
            }
        } else{
            action()
        }
    }

}