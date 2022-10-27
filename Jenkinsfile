pipeline{
    agent any
    environment {
         ECR_REPO = "347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr"
         AWS_CREDENTIALS="cosmost_aws_credentials"
         GIT_CREDENTIAL_ID = "cosmost_JenkinsKey"
         NAME = "cosmost-ecr"
         GIT_URL="https://github.com/CosMost-BE/cosmost-board.git"
    }
    stages {
        stage('Pull') {
            steps {
                git url:"${GIT_URL}", branch:"master", poll:true, changelog:true,credentialsId: "${GIT_CREDENTIAL_ID}"
            }
        }
        stage('Build') {
            steps {
                sh "docker build -t ${NAME} ."
                sh "docker tag ${NAME}:latest ${ECR_REPO}/${NAME}:latest"
            }
        }
        stage('ECR Upload'){
            steps {
                script{
                    try{
                        withAWS(credentials: "${AWS_CREDENTIALS}", role: 'arn:aws:iam::347222812711:user/cosmost-depoly', roleAccount: "deploy_user", externalId: 'externalId'){
                            sh "aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr"
                            sh "docker push ${ECR_REPO}/${NAME}:latest"
                        }
                    }catch(error){
                        print(error)
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
            post{
                success {
                    echo "The ECR Upload stage successfully."
                }
                failure{
                    echo "The ECR Upload stage failed."
                }
            }
        }
    }
}