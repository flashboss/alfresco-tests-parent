set -e
cd alfresco-tests
git checkout 4.2.c
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
mvn clean install -o
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home
mvn deploy -Prelease-sign-artifacts
git tag 4.2.c.4
git push
git checkout 4.2.6.6
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
mvn clean install -o
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home
mvn deploy -Prelease-sign-artifacts
git tag 4.2.6.6.27
git push
git checkout 4.2.8
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home
mvn clean install -o
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home
mvn deploy -Prelease-sign-artifacts
git tag 4.2.8.5
git push
git checkout 5.0.d
mvn clean deploy -Prelease-sign-artifacts
git tag 5.0.d.23
git push
git checkout 5.1.g
mvn clean deploy -Prelease-sign-artifacts
git tag 5.1.g.23
git push
git checkout 5.2.f
mvn clean deploy -Prelease-sign-artifacts
git tag 5.2.f.24
git push
git checkout 5.2.7
mvn clean deploy -Prelease-sign-artifacts
git tag 5.2.7.20
git push
git checkout 6.0.0-ea
mvn clean deploy -Prelease-sign-artifacts
git tag 6.0.0-ea.27
git push
git checkout 6.0.2-ea
mvn clean deploy -Prelease-sign-artifacts
git tag 6.0.2-ea.15
git push
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-11.0.9.jdk/Contents/Home
git checkout 6.1.0-ea
mvn clean deploy -Prelease-sign-artifacts
git tag 6.1.0-ea.25
git push
git checkout 6.2.0-ea
mvn clean deploy -Prelease-sign-artifacts
git tag 6.2.0-ea.27
git push
git checkout 6.2.2
mvn clean deploy -Prelease-sign-artifacts
git tag 6.2.2.11
git push
git checkout 7.0.0-A20
mvn clean deploy -Prelease-sign-artifacts
git tag 7.0.0-A20.18
git push
git checkout 7.1.0
mvn clean deploy -Prelease-sign-artifacts
git tag 7.1.0.13
git push
git checkout 7.2.0
mvn clean deploy -Prelease-sign-artifacts
git tag 7.2.0.5
git push
git checkout 7.2.1
mvn clean deploy -Prelease-sign-artifacts
git tag 7.2.1.6
git push
export JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home
git checkout 7.3.0
mvn clean deploy -Prelease-sign-artifacts
git tag 7.3.0.6
git push
git checkout 7.4.0
mvn clean deploy -Prelease-sign-artifacts
git tag 7.4.0.3
git push
