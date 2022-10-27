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
        ENCRYPTED_TOKEN="eyJwYXlsb2FkIjoiNWFrYXlsUG1PQkpYTCtzbXF4UVhYWm5lMDBydHJYdzBlSlFEVnQvRFZXV1ZYTFpFeDEzdnQ0Z2g0WE93d1RXdzVwQ2MrMFZXQlhFbytNYjJ5QisrckgwU3BESjFJUythZUZMbGNpUVM0MnNVdjJ1RERnMlVuM0xCK2k1OVpzZ3VmN2N5YVJ3eW04bllUaFZ2S2lBR05DZDJzMXVRUW1hZ0RaV2YxWXoxOTJ4bVRBM0JUTlg4VUNMNmpJUzQyT3BSdUZaVjNpS1ZqZ3B6bW1XYkxyRlV3OHhCckh4bUsxdVF4K1VlZXZHZDFvU2dpYndoclo5Tm9MMlVlTTBUcGRQR2ZkVExackNSaTNra3VxYklxOWF6OHU0Z3NKVFV1M0cyMTh3MitBeE5lMTF0RFp0UDhyMlZTbjdud3FWMm1Yc2dEY1pudW5mNkhQTXp5ZysrQXo3a0FNNFBTOHMrWXdMaHdENE43VGR5U3MvU0dpMTJrb2d4MkJocm00QWVvZVFqTXhxTWFNTW1VMDFWRFVaSkJKclNGQStWeW10SVNXdmlMSXBTalZ2N3Bubng0M21HdWM4ZXE3UVlXMEFBOGtxcE02N05WMDNoN1RvU2dEcmg3NUwvT082L2YvV0dqd2RZYmM1WitXRE55YUsyL0M3ZGVSaTBONnZJM3B2UHJOM2pVekZ1YVNXcTQwRSs1T0xaS0xoV1h2bkZQVENxT3FpOTlCcERJR0x2RGFTMG5SbHZyOVY3RjNlWFpUSHpQVlN0RVhsSE9zdkVrdzF4b21kSHA2aVZRclorR3BVR1VvWnEzanVQVGFxUG9hWVorS1Q0bnZNU08zbzJVVytINHZ5VVVBSk1DSkQ1VkNJZVU4QWR2QjZCc0NjUy9uQVhUdFVFQ1FpeDE0WUx5ajJEYlk5RnRyRWlleEx2V1ZRd3VCQWVqV2RHbmJDMVZxd2FoM0Z1ZWxzTldVWGRWMGE4L2granFrMHhWcU5ST3VQODR6VmhtMUJ6ZzQ5TGQyWkFsenlXa2pVNzRQWDUxdE5ReDhRMDFHYVVoMm9VQk9KdXM4cGg2K3FrdU5IbmZna1p5Rk9IbEVZT1pMRERnd000M0hKSytML1JmSGhabU9qWHpaQk1tUS9ReTdkQ1QyNTdqamNhaTc2Qit0NVdWWkZFZklZYU8yUkg5Y1VsSVhwM0xla1dsTXhBQURNVjc0ZE9Uc3VKVWRaYW8yWVozdXh3ckVPUEJQOWFLRTRGTThRK25mTG5zazRkWk10QUJHMlhJMTdWajQ1RzNGWWJNWWM2dG0xYU5neEJmVEh1VUJDd0xiK1lyamRtYm1LYnM5ZW9xbGp6ZSt3Mnk1NW9zcHBBVW1ESzROQ3ZWNDVlVnA1U2s2ZmRmaE1aSmpFMUlIZFZicHQ5RGN4d1J5MD0iLCJkYXRha2V5IjoiQVFJQkFIaEFPc2FXMmdaTjA5V050TkdrWWM4cXAxMXhTaFovZHJFRW95MUhrOExYV2dGalRMdDdBQ2RsQWRFY1hYOEZEU0EvQUFBQWZqQjhCZ2txaGtpRzl3MEJCd2FnYnpCdEFnRUFNR2dHQ1NxR1NJYjNEUUVIQVRBZUJnbGdoa2dCWlFNRUFTNHdFUVFNOFFrbUZsR0VHaVptditNUEFnRVFnRHZUeVVGRXc5aFR2WWVKbmtrVFZjRFVQelpKMnNjdVl3ekwrcU56QWNCQmlxaFVlM3BpQXVBbnJ3UWYxekhYWXVZU280Y0VKQkdrVnFTUWFBPT0iLCJ2ZXJzaW9uIjoiMiIsInR5cGUiOiJEQVRBX0tFWSIsImV4cGlyYXRpb24iOjE2NjY5MTk2MDJ9"
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