#!/bin/bash

# AppleGame JAR 빌드 스크립트
# 사용법: ./build.sh <version>
# 예: ./build.sh 1.0
# 또는 환경변수 사용: APP_VERSION=2.0 ./build.sh

if [ $# -eq 0 ] && [ -z "$APP_VERSION" ]; then
    echo "사용법: $0 <version>"
    echo "예: $0 1.0"
    echo "또는 환경변수 사용: APP_VERSION=2.0 $0"
    exit 1
fi

# 파라미터가 있으면 파라미터 사용, 없으면 환경변수 사용
if [ $# -gt 0 ]; then
    VERSION=$1
    export APP_VERSION=$VERSION
else
    VERSION=$APP_VERSION
fi

echo "AppleGame JAR 파일을 빌드합니다 (버전: $VERSION)"
echo "환경변수 APP_VERSION=$APP_VERSION 으로 설정했습니다."

# 빌드 실행
echo "빌드를 시작합니다..."
./gradlew clean build

if [ $? -eq 0 ]; then
    echo ""
    echo "빌드가 성공적으로 완료되었습니다!"
    echo "생성된 JAR 파일: build/libs/AppleGame-$VERSION.jar"
    echo ""
    echo "실행 방법: java -jar build/libs/AppleGame-$VERSION.jar"
else
    echo "빌드에 실패했습니다."
    exit 1
fi
