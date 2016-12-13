#!groovy

node {
  stage 'Stage Checkout'
  checkout scm

  stage 'Stage Build & Publish'
  echo "My branch is: ${env.BRANCH_NAME}"
  sh "./gradlew clean check test oneJar artifactoryPublish"

  stage 'Stage Deploy'
  git ([url: 'https://github.com/anperez78/ansible-application-service.git', branch: 'master'])
  sh "ansible-galaxy install --role-file=./requirements.yml --roles-path=./roles/ --force"
  wrap([$class: 'AnsiColorBuildWrapper', colorMapName: "xterm"]) {
        ansiblePlaybook(
            playbook: 'playbook.yml',
            inventory: 'inventory/sandbox',
            colorized: true)
  }
}
