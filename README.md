# Everyday Friend
AI를 활용한 사용자 표정과 주변 환경에 따른 댁내 힐링 서비스


2024년 한이음 공모전 입선작

## 👨‍👩‍👧‍👦 팀원 소개
하드웨어 및 서버 - 정수영, 박성현, 이가영


앱 개발 - 임채은

## 📃 프로젝트 소개
현대 사회에서는 많은 사람들이 바쁜 일상 속에서 자신의 감정을 숨기고 살아간다. 이 앱은 사용자의 가정에 설치된 카메라로 사용자의 표정을 분석해 그에 따른 감정을 인식하고, 각 감정에 적합한 힐링 콘텐츠를 제공한다.


## 🖇️ 개발 기술
RaspberryPi 5, Android Studio, Kotlin, MySQL, AWS


## 📱안드로이드 앱 화면 구성도
![image02](https://github.com/user-attachments/assets/9ea9e5ea-b4c9-4a02-9733-652b779ba553)


## 📨 서비스 흐름도
![image](https://github.com/user-attachments/assets/02bab63d-a2db-4d33-9f7b-933ef7fbb0bc)
1) 라즈베리파이의 카메라 모듈을 사용하여 사용자가 로그인을 하기 전까지 사용자의 표정을 인식하여 저장함

2) App에서 회원가입시, main서버의 mysql DB에 정보 저장됨

3) App에서 로그인시, 라즈베리파이에 ID를 포함한 user의 로그인 기록이 넘어감

4) 넘어온 user의 감정 디렉토리 내의 이미지 수를 카운트해서 main서버의 mysql DB로 ID, 감정, 날짜등의 정보를 저장함

5) App에서 DB의 저장된 user의 감정을 가져옴

6) App에서 가져온 감정을 토대로 맞춤형 콘텐츠를 제공함




