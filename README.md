# AppleGame-Java

Apple Game은 Java Swing을 사용한 간단한 퍼즐 게임입니다. 10x10 보드에서 두 개의 숫자를 선택하여 합이 10이 되는 쌍을 찾아 제거하는 게임입니다.

## 게임 규칙

1. 10x10 보드에 1-9 사이의 숫자가 랜덤하게 배치됩니다
2. 두 개의 셀을 차례로 클릭합니다
3. 선택한 두 숫자의 합이 10이면 해당 셀들이 제거됩니다
4. 합이 10이 아니면 다시 선택해야 합니다

## 폴더 구조

```
AppleGame-Java/
├── src/
│   └── AppleGameGUI.java     # 메인 게임 클래스
├── build/
│   └── libs/                 # 빌드된 JAR 파일들
├── gradle/                   # Gradle 래퍼 파일들
├── build.gradle              # Gradle 빌드 설정
├── build.sh                  # Linux/macOS 빌드 스크립트
├── build.bat                 # Windows 빌드 스크립트
└── README.md
```

## 빌드 방법

### 환경변수 사용

#### Linux/macOS:
```bash
APP_VERSION=1.0 ./build.sh
```

#### Windows:
```batch
$env:APP_VERSION="1.0"; .\build.bat
```

#### PowerShell:
```powershell
$env:APP_VERSION="1.0"; .\build.bat
```

### 파라미터 사용

#### Linux/macOS:
```bash
./build.sh 1.0
```

#### Windows:
```batch
build.bat 1.0
```

### 직접 Gradle 사용

#### Linux/macOS:
```bash
APP_VERSION=2.0 ./gradlew clean build
```

#### Windows:
```batch
set APP_VERSION=2.0 & gradlew.bat clean build
```

### 기본값 사용 (버전 1.0)
```bash
./gradlew clean build
```

## 실행 방법

빌드 후 생성된 JAR 파일을 실행:

```bash
java -jar build/libs/AppleGame-<version>.jar
```

예시:
```bash
java -jar build/libs/AppleGame-1.0.jar
java -jar build/libs/AppleGame-2.5.jar
```

## 개발 환경

- **Java**: JDK 8 이상
- **빌드 도구**: Gradle
- **GUI 프레임워크**: Java Swing
- **IDE**: Visual Studio Code (권장)

## 종속성 관리

`JAVA PROJECTS` 뷰를 통해 종속성을 관리할 수 있습니다. 자세한 내용은 [여기](https://github.com/microsoft/vscode-java-dependency#manage-dependencies)를 참조하세요.

## 빌드 스크립트 기능

### Linux/macOS (`build.sh`)
- 환경변수 `APP_VERSION` 지원
- 파라미터 우선순위 처리
- 자동 빌드 및 결과 안내
- 에러 처리

### Windows (`build.bat`)
- 환경변수 `APP_VERSION` 지원
- Windows 배치 파일 문법
- gradlew.bat 사용
- 지연된 환경변수 확장
