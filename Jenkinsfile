#!groovy

node {
  stage 'SCM Checkout'
  checkout scm

  stage 'Build & Unit Tests'
  echo "My branch is: ${env.BRANCH_NAME}"
  sh "./gradlew clean check test shadowJar"

  stage 'Publish Artifact'
  sh "./gradlew artifactoryPublish"

  stage 'Deploy to Sandbox'
  ws('workspace/demo-service/deploy') {
      git([url: 'https://github.com/anperez78/vagrant-demo-sandbox.git', branch: 'master'])
      sh "ansible-galaxy install --role-file=./requirements.yml --roles-path=./roles/ --force"
      sh "ansible-playbook deploy-service-api.yml -i inventory/sandbox"
  }

  stage 'Integration Tests'
  sh "./gradlew integration"
}
