#!groovy

node {
  stage 'SCM Checkout'
  checkout scm

  stage 'Build & Publish'
  echo "My branch is: ${env.BRANCH_NAME}"
  sh "./gradlew clean test shadowJar artifactoryPublish"

  stage 'Deploy to Sandbox'
  git ([url: 'https://github.com/anperez78/ansible-application-service.git', branch: 'master'])
  sh "ansible-galaxy install --role-file=./requirements.yml --roles-path=./roles/ --force"
  sh "ansible-playbook playbook.yml -i inventory/sandbox"
}
