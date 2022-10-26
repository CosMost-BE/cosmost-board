pipeline{
    agent any

    environment {
       ECR_REPO = "----------------.dkr.ecr.ap-northeast-2.amazonaws.com"
       AWS_CREDENTIALS="aws credential id를 입력하세요."
       GIT_CREDENTIAL_ID = "git credential id를 입력하세요."
       NAME = "hello"
       VERSION = "${env.BUILD_ID}-${env.GIT_COMMIT}"
       GIT_URL="http://your_git_server_ip/git/hello.git"
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
                        withAWS(credentials: "${AWS_CREDENTIALS}", role: 'arn:aws:iam::"----------------:role/jenkins-deploy-role', roleAccount: "deploy_user", externalId: 'externalId'){
                            sh "aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin "----------------.dkr.ecr.ap-northeast-2.amazonaws.com"
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