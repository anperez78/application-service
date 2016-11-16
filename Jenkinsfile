#!groovy

node {
  stage 'Stage Checkout'
  checkout scm
  sh 'git submodule update --init'

  stage 'Stage Build'
  echo "My branch is: ${env.BRANCH_NAME}"
  sh "./gradlew clean check test oneJar"
}
