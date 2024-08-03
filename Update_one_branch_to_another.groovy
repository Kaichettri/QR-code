pipeline {
    agent any

    environment {
        GIT_REPO = 'https://github.com/Kaichettri/QR-code'
        SOURCE_BRANCH = 'main'
        TARGET_BRANCH = 'prod'
    }

    stages {
        stage('Clone Repository') {
            steps {
                // Clone the repository
                git branch: "${SOURCE_BRANCH}", url: "${GIT_REPO}"
            }
        }

        stage('Run Python Scripts') {
            steps {
                // Run all Python scripts
                script {
                    def pythonScripts = findFiles(glob: '**/*.py')
                    pythonScripts.each { script ->
                        sh "python3 ${script.path}"
                    }
                }
            }
        }


        stage('Commit Changes') {
            steps {
                script {
                    sh """
                    git checkout -b temp-branch
                    git add .
                    git commit -m "Executed Jupyter notebooks and updated results"
                    git checkout ${TARGET_BRANCH}
                    git merge temp-branch
                    git push origin ${TARGET_BRANCH}
                    """
                }
            }
        }
    }

    post {
        success {
            // Post build actions
            echo 'Pipeline completed successfully!'
        }
        failure {
            // Actions on failure
            echo 'Pipeline failed!'
        }
    }
}
