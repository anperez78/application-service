#!groovy

node {
  stage 'Stage Checkout'
  checkout scm

  stage 'Stage Build & Publish'
  echo "My branch is: ${env.BRANCH_NAME}"
  sh "./gradlew clean check test oneJar artifactoryPublish"
}
