pipeline{
    agent any
    tools {
      git "git"
    }
    environment {
        ECR_REPO = "347222812711.dkr.ecr.ap-northeast-2.amazonaws.com/cosmost-ecr"
        AWS_CREDENTIALS="cosmost_aws_credentials"
        GIT_CREDENTIAL_ID = "Back_CodeCommit"
        NAME = "cosmost-ecr"
        GIT_URL="https://github.com/CosMost-BE/cosmost-board.git"
        ENCRYPTED_TOKEN="eyJwYXlsb2FkIjoiSjg2anFqWVZuZXYxTmV5U2R5cCtIa1pQSUhRd1JzOFh0RHUxajBmdFJTWjRkM1h1TWkyNWJ3MXkvUDhnS1VtODhmUWVhSFhIOEM2UGtkNnJKZlVTbE1XQ3d
                         MdVpEVDAxWE9aK1gzQkduSzNzUHNPQjRnMmxiRlUwdXFiOC9Hc21nYU1QRTBkT0xWcWtJOEhWUTBvUWphMDBxeDVXNEJtK2dDZGQxZCtQcUhjanF2ZFZ6MkcvNUg2NDZvRlhXOTI1T29XVTBwSUJXNkc4bjMyO
                         HRtUUpWbHcwaDF6enFUZ0RPZjNXTXduTnNhMkplTStYaDZ4VFhKWjVCZVMwUDlmQ05xUzNDbWxRelF5Qmc3N010NTR0N1RqQS8xOHdaVjRLMGI3YlYybnEzQkVOa3ZDZmFyOTlrMjUybHIxWk4wYnRMaXJDRVB
                         WcVZ2OXNmYVBqQ3gxWXloMW5icGRMWTIveVI2VXErQkNQTVB6SzNIc1pHdGxPZHZJTFh1bTcraldXNm5LbitTbktWWVYrRDc1QmVUTWZhTC9VSzFCUXVYMVk1clNxYjhHU2ZGOUdCRGZrcEdXWlFYWXJTaks5b
                         nlzc1oxZE5tV1d4NS9uRGl3UTJwOHJCM0ZvWWc2L2dTZFdyWkY4Ui9sc1owcmtRZFdJTzd5bzQ3V1FyZjJnSWdWem1iV2I1aG53c3RVN2RnaDFtYW9TMXo4dVlONjNuNHgwUmF2SE5pUmxSRmxsM1JzSWhkaGx
                         qZWJxMTJYUXJ0UU9lRS9tZVNJeXF3WlZ3OGpzMGdZUzEzSmRlQU51YUN2My9sNlBtczkxL3lja0ZRQWg0WXVvU00zRUhxVy9Oc1NjNXpTVm5XZ09LeFd4SlQ1NHNLWThBY29lU3BYZllOWmpnZGtmRGZaWkxUK
                         3ZQMlAxYWRqSFdDUThySGhQaXFkdStlMk9kckpoY3VwdUdYVmdRNlV5dHAvcEVMOHgxZkNvSldEZkdlYzFxeEFLUHBYL0t4UjNHb3ZqcWVvb2FxSUZVeENUMGxabWdWRHZYMzNjSkNFZGd6bWZaTUxQUGg0Zmd
                         iai9sTlZqUEFjSytHTkJrNW0rN092RDRPbkg3djlhOHNDWURkNm9UTWw1VWV1eitzQXBzeG1aZGY5VTZqdGtNaU5IMWgxU3NBU2hiZm55SXpCOENkd3djMy93ekN5TUFkR1JvbHZKNUxndjM2UzlZNzFJRXJnU
                         UJkdnhFVkp0U1JqZE5XYUMycE5HNHlBajdORmFSSjR4OStjcTAwTnlySjl4VHJKNDVGdzVoWWg1aW9YZ2M3dktJMi9oTW5oS1ZJUDFyelhsd2E5cHY5OG9HdW9PSjE1YkMxaldyT3UwQWpLc0NXbEQvTlVTRzN
                         UTzN1em5QWFk3SDBwWkN2RDRNVm44REh4ST0iLCJkYXRha2V5IjoiQVFJQkFIaEFPc2FXMmdaTjA5V050TkdrWWM4cXAxMXhTaFovZHJFRW95MUhrOExYV2dHWnY0K2MvYk8vYUR2U0E3aitOZnp5QUFBQWZqQ
                         jhCZ2txaGtpRzl3MEJCd2FnYnpCdEFnRUFNR2dHQ1NxR1NJYjNEUUVIQVRBZUJnbGdoa2dCWlFNRUFTNHdFUVFNeUtyWVVFQ3YzclpvUkMyMUFnRVFnRHY4ZlVXRUFzaHVlb1A0TU5KSjZoSlVtK1R1VkZ4Y0E
                         0N0trS2ZVa0s4WkNVbVdxVkVpb21xTWZrYXdrWVZOMjZ1bzVVcW1MWUMra1I4em1BPT0iLCJ2ZXJzaW9uIjoiMiIsInR5cGUiOiJEQVRBX0tFWSIsImV4cGlyYXRpb24iOjE2NjY5MzIxNjF9"
    }
    stages {
        stage('Pull') {
            steps {
                git url:"${GIT_URL}", branch:"main",
                poll:true,
                changelog:true,
                credentialsId: "${GIT_CREDENTIAL_ID}"
            }
        }
        stage('Build') {
            steps {
                sh "docker build -t ${NAME} ."
            }
        }
        stage('ECR Upload'){
            steps {
                script{
                    try{
                        sh "aws ecr --region ap-northeast-2 | docker login -u AWS -p ${ENCRYPTED_TOKEN} ${ECR_REPO}"
                        sh "docker tag ${NAME}:latest ${ECR_REPO}:latest"
                        sh "docker push ${ECR_REPO}"
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