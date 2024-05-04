#!/bin/bash

set -e

findJava() {
    case $1 in
        "7")
            echo "/Library/Java/JavaVirtualMachines/jdk1.7.0_80.jdk/Contents/Home"
            ;;
        "8")
            echo "/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home"
            ;;
        "11")
            echo "/Library/Java/JavaVirtualMachines/jdk-11.0.9.jdk/Contents/Home"
            ;;
        "17")
            echo "/Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home"
            ;;
        *)
            ;;
    esac
}

upgrade() {
   local versionl=$version
   ((versionl++))
   echo '<version>'$branch'.'$version'</version> <version>'$branch.$versionl'</version>'
   sed -i "" -e "s^<version>$branch.$version</version>^<version>$branch.$versionl</version>^" pom.xml
   sed -i "" -e "s^<version>$branch.$version</version>^<version>$branch.$versionl</version>^" alfresco-tests/pom.xml
   if [ -f "alfresco-tests-activiti-sample/pom.xml" ]; then
      sed -i "" -e "s^<version>$branch.$version</version>^<version>$branch.$versionl</version>^" alfresco-tests-activiti-sample/pom.xml
   fi
   sed -i "" -e "s^<version>$branch.$version</version>^<version>$branch.$versionl</version>^" alfresco-tests-sample/pom.xml
   sed -i "" -e "s^<version>$branch.$version</version>^<version>$branch.$versionl</version>^" alfresco-tests-ws-sample/pom.xml
   sed -i "" -e "s^.$version</version>^.$versionl</version>^" README.md
   git commit -am "upgrade";git push
}

release() {
   local branch=$1
   local version=$2
   local javacommand=$3
   local deploycommand=$4
   git checkout $branch
   JAVA_HOME=$(findJava $javacommand) mvn clean install
   git tag $branch.$version
   git push --tags
   cd alfresco-tests
   JAVA_HOME=$(findJava $deploycommand) mvn deploy -Prelease-sign-artifacts -DskipTests
   cd ..
   upgrade $branch $version
}

release 23.2.1 2 17 17
release 23.1.0 4 17 17
release 7.4.1.3 4 17 17
release 7.4.1.1 3 17 17
release 7.4.0.1 9 17 17
release 7.4.0 12 17 17
release 7.3.0 15 17 17
release 7.2.1 15 11 11
release 7.2.0 14 11 11
release 7.1.0 22 11 11
release 7.0.0-A20 26 11 11
release 6.2.2 19 11 11
release 6.2.0-ea 33 11 11
release 6.1.0-ea 31 11 11
release 6.0.2-ea 21 8 8
release 6.0.0-ea 33 8 8
release 5.2.7 29 8 8
release 5.2.g 0 8 8
release 5.2.f 30 8 8
release 5.1.g 29 8 8
release 5.0.d 29 8 8
release 4.2.8 12 7 8
release 4.2.6.6 32 7 8
release 4.2.c 9 7 8
