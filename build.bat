@echo off
setlocal enabledelayedexpansion

REM AppleGame JAR 빌드 스크립트 (Windows)
REM 사용법: build.bat <version>
REM 예: build.bat 1.0
REM 또는 환경변수 사용: set APP_VERSION=2.0 & build.bat

if "%1"=="" (
    if "%APP_VERSION%"=="" (
        echo 사용법: %0 ^<version^>
        echo 예: %0 1.0
        echo 또는 환경변수 사용: set APP_VERSION=2.0 ^& %0
        exit /b 1
    )
)

REM 파라미터가 있으면 파라미터 사용, 없으면 환경변수 사용
if not "%1"=="" (
    set VERSION=%1
    set APP_VERSION=%1
) else (
    set VERSION=%APP_VERSION%
)

echo AppleGame JAR 파일을 빌드합니다 (버전: !VERSION!)
echo 환경변수 APP_VERSION=!APP_VERSION! 으로 설정했습니다.

REM 빌드 실행
echo 빌드를 시작합니다...
call gradlew.bat clean build

if !ERRORLEVEL! equ 0 (
    echo.
    echo 빌드가 성공적으로 완료되었습니다!
    echo 생성된 JAR 파일: build\libs\AppleGame-!VERSION!.jar
    echo.
    echo 실행 방법: java -jar build\libs\AppleGame-!VERSION!.jar
) else (
    echo 빌드에 실패했습니다.
    exit /b 1
)

endlocal