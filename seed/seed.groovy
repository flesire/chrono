freeStyleJob("${SEED_PROJECT}-${SEED_BRANCH}-build") {
    logRotator(-1, 40)
    jdk 'JDK7'
    scm {
        git {
            remote {
                url "https://github.com/flesire/chrono.git"
                branch "origin/${BRANCH}"
            }
            wipeOutWorkspace()
            localBranch "${BRANCH}"
        }
    }
    steps {
        gradle ''' clean assembleDebug'''
    }
}