// pipeline{
//     agent any
//     environment {
//         ECR_REPO = "347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr"
//         AWS_CREDENTIALS="cosmost_aws_credentials"
//         GIT_CREDENTIAL_ID = "Back_CodeCommit"
//         NAME = "cosmost-ecr"
//         GIT_URL="https://github.com/CosMost-BE/cosmost-board.git"
//         ENCRYPTED_TOKEN="eyJwYXlsb2FkIjoibDBYb2I4dzNiVXFuT2JEWGRUOE13SjVSTENUcWN4L2dpVTB4cXJKOTZUdmVjTGJQekhDdVBpdjBwR0VZTVFCVVBYanlsN1BIeTJnbVdoQXY3ZWVRRGI3K0crU2o2cTZ3UG9Sdko2K0xDN1VuMlFoemN1cVhHTVp3aVErRFNqems2SGRGQ2hiNnJjNVhJNnJldHFYcWtMWDhmNC9Jai9Fb0ZDWW1vb0QxVmVzU2liMFJoVEtmWmszdUpPSHg0cjkwWjZFdjM5b2xKSEhBWEN2MUlMOGh4Q0JQRVB5czdzdFNHdHpYMjV2YTZMWXU1RVR3b3pDRFJTRWt2U21Bdi92M2twU0wrUTNjWlRQMzRXOU9nallpTWhRcXhyb2lYeWhGN1RwVHpqZ3JaL05lQ1owVGQ1Q0VIdVFvU290VjFqYThEWUhwNFQxcUNOSW9vMStYK1g5WmdqQktGcXlRY0xJWjI0bTFUSmZHY3lZbEE3ZGpwVG9iS2U4cHJHV0lneG4rUGdnVkwySExGUURFYzdIalhsNkxHMW5ObTJDb1hZdGUwWUc5M29ORFlCQzJNUWtkaVFtL1F6aWM5b2krUS9XU2tJa2lwWG5GT1VxM3I1V3hjVGdnMVVPS1N0RC9xemJmVFNzSWpBTnRYVFRralowLzlQRmxHWXVvTU1LVWw4dkhZNndVS2RDL0VKSHU4QjlnV2JRVSsxd1dYaW95aHhxNERoenV1cmYyQzdQM3pVMXdZc0FSVzI3WDhQQVJJTnNiVHRFb2dMYkNXU2pHQUd1dDFOekJ3SUc5VVMyazdyRzR0MjFUU0kySkM3OTVkdkltZUZXSmozdnVuMnFXanAzNk1KUnZwZTJCeFpiRFVvS1lPRHoxQmtzMGxQb01ETFVWbGl0MXF3YWl0cHM3bTg3OHBJajJZNk50T01NNDNia0E5akJwcU0zYzlQQlpvN2ZIdWY2c3hPVG5haEhFTUJ2Z2dVdktJM0NqQzBLWi9waGlpdTNmUlVkeVUvQnkzRVFkaDN1WkMzcS9WVnJvZWFzUGxaQzMvditmUzFnNEh0VHVWakdhK01ENTV1ZjR4NGZxQlNseklKY1ZtOUlQSDRucWVHRXpQQytqaWlIZ09TMDVwRjlZdDBjTXJnaGdsR1RPYVJTdjlMREJUNFphRDQwSUZrZWVQS1FoOWxSVEkyenN2Ukt1NjZhcFJITEhjSXF2ZnVwdTBJb1lYNlJTM2Z0amNySExpUk5SUW1QQllFMFI4MVE4S0pxbHlZSEduYTl2dWFJN2JuOFpzdDZ1T0gxdGJXSnhKZEQ1Rk45ZnpNaEFNTDRwM1EzMlZxeGZQd052dnllWFdNWGNJNWluSFlaV29oamhQbjU5eklodnJ5YndhalNGc2cxc2JvQ1oxS0hUSjVQVWE3ST0iLCJkYXRha2V5IjoiQVFJQkFIaEFPc2FXMmdaTjA5V050TkdrWWM4cXAxMXhTaFovZHJFRW95MUhrOExYV2dFYksyRGZHaDZFam5ld1lIMXpsQTUwQUFBQWZqQjhCZ2txaGtpRzl3MEJCd2FnYnpCdEFnRUFNR2dHQ1NxR1NJYjNEUUVIQVRBZUJnbGdoa2dCWlFNRUFTNHdFUVFNQlVuRUV1ekYwL1poYm8zK0FnRVFnRHZpWnhVejhIV2FyNmZzb3M2OXJjeTV3bTFHbzhEZGFWbDRobTZ5UUlKa0pSQW1lQzU2eUZMaU1tUEhHQzd6czE4aDJhZysyT05RV2IwVTRnPT0iLCJ2ZXJzaW9uIjoiMiIsInR5cGUiOiJEQVRBX0tFWSIsImV4cGlyYXRpb24iOjE2NjY5NjI2NDF9"
//
//     }
//     stages {
// //         stage('Pull') {
// //             steps {
// //                 git url:"${GIT_URL}", branch:"*/feature/report-fix",
// //                 poll:true,
// //                 changelog:true,
// //                 credentialsId: "${GIT_CREDENTIAL_ID}"
// //             }
// //         }
//         stage('Build') {
//             steps {
//                 sh "docker build -t ${NAME} ."
//             }
//         }
//         stage('ECR Upload'){
//             steps {
//                 script{
//                     try{
//                         sh "aws ecr --region ap-northeast-2 | docker login -u AWS -p ${ENCRYPTED_TOKEN} ${ECR_REPO}"
//                         sh "docker tag ${NAME}:latest ${ECR_REPO}:latest"
//                         sh "docker push ${ECR_REPO}"
//                     }catch(error){
//                         print(error)
//                         currentBuild.result = 'FAILURE'
//                     }
//                 }
//             }
//             post{
//                 success {
//                     echo "The ECR Upload stage successfully."
//                 }
//                 failure{
//                     echo "The ECR Upload stage failed."
//                 }
//             }
//         }
//     }
// }

pipeline{
    agent any
    options {
        skipStagesAfterUnstable()
    }
        environment {
        ECR_REPO_URI = "347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr"
        AWS_CREDENTIALS="TEST_CICD_JENKINS"
    }
    stages {
          stage('Clone repository') {
            steps {
                script{
                checkout scm
                }
            }
        }
        stage('Build') {
            steps {
                script{
                  app = docker.build("${ECR_REPO_URI}")
                }
            }
        }
        stage('Test'){
            steps {
                  echo 'Empty'
            }
        }
        stage('ECR Upload') {
            steps {
                script{
                    sh 'rm  ~/.dockercfg || true'
                    sh 'rm ~/.docker/config.json || true'
                    docker.withRegistry("https://${ECR_REPO_URI}", "ecr:ap-northeast-2:${AWS_CREDENTIALS}") {
                    app.push("${env.BUILD_NUMBER}")
                    app.push("board-service")
                    }
                }
            }
        stage('Deploy') {
            steps {
                script{


                    }
                }
            }
        }
    }
}