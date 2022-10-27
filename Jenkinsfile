pipeline {
    agent any
     environment {
           ECR_REPO = "347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr"
           AWS_CREDENTIALS="cosmost_aws_credentials"
           GIT_CREDENTIAL_ID = "cosmost_JenkinsKey"
           NAME = "cosmost-ecr"
           GIT_URL="https://github.com/CosMost-BE/cosmost-board.git"
        }
    stages {
         stages {
            stage('Pull') {
                steps {
                    git url:"${GIT_URL}", branch:"/feature/report-fix", poll:true, changelog:true,credentialsId: "${GIT_CREDENTIAL_ID}"
                }
            }
        stage('Build') {
                steps {
                    sh "docker build -t ${NAME} ."
                    sh "docker tag ${NAME}:latest ${ECR_REPO}/${NAME}:latest"
                }
            }
        stage('ECR Upload') {
            steps{
                script{
                    try {
                        withAWS(credential: '${AWS_CREDENTIALS}', role: 'arn:aws:iam::347222812711:user/cosmost-depoly', roleAccount: '347222812711', externalId:'externalId') {
                            sh 'aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin 347222812711.dkr.ecr.ap-northeast-2.amazonaws.com'
                            sh 'docker build -t cosmost-ecr .'
                            sh 'docker tag cosmost-ecr:latest 347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr:latest'
                            sh 'docker push 347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr:latest'
                        }
                    } catch (error) {
                        print(error)
                        echo 'Remove Deploy Files'
                        sh "sudo rm -rf /var/lib/jenkins/workspace/${env.JOB_NAME}/*"
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
            post {
                success {
                    echo "The ECR Upload stage successfully."
                }
                failure {
                    echo "The ECR Upload stage failed."
                }
            }
        }
        stage('Deploy'){
            steps {
                script{
                    try {
                        withAWS(role: 'cosmost-depoly', roleAccount: '347222812711', externalId:'externalId') {
                            sh"""
                                aws ecs update-service --region ap-northeast-2 --cluster cosmost --service ecs-service --force-new-deployment
                            """
                        }

                    } catch (error) {
                        print(error)
                        echo 'Remove Deploy Files'
                        sh "sudo rm -rf /var/lib/jenkins/workspace/${env.JOB_NAME}/*"
                        currentBuild.result = 'FAILURE'
                    }
                }
            }
            post {
                success {
                    echo "The deploy stage successfully."
                }
                failure {
                    echo "The deploy stage failed."
                }
            }
        }
    }
}