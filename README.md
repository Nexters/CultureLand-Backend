# CultureLand

> Nexters 15th 컬쳐랜드팀 프로젝트
>
> 나의 문화생활에 대한 소감을 기록하는 앱

## Member

이름 | 역할
:--: | :--:
박경철 | PM & Backend (API 개발 담당)
이태현 | Backend (인증 담당)
이지은 | Backend (문화생활 데이터 담당)
임지영 | Mobile
김현준 | Mobile
장혜연 | Design & Default
박희주 | Design

## Environment

- Spring boot 2.1.6
- Spring Data JPA
- Maven
- git & slack & Zeplin
- MySQL 8.0.11
- AWS EC2
- jenkins

## 주요 기능

1. 문화 생활 조회

   현재 또는 미래에 예정되있는 문화생활을 조회하는 기능

2. 소감 기록

   문화생활을 하고 난 뒤 간단한 소감 등을 기록하는 기능

3. 알림

   문화생활을 기록하는 것에 초점을 맞춰 사용자가 알림 받을 날을 설정하면 앱에서 해당 시간에 알림을 주는 기능

## RESTAPI 설계

### User (CRUD)

URL | 요청 | 설명
:--: | :--: | :--:
/users | POST | 회원가입
/users/{userId} | GET | 마이페이지
/users/{userid} |  PUT | 회원 수정
/users/{userid} |  DELETE | 회원 탈퇴

### Diary (CRUD)

URL | 요청 | 설명
:--: | :--: | :--:
/users/{userId}/diarys | GET |  유저의 모든 소감을 조회하는 uri
/users/{userId}/diarys/{diaryId} | GET |  유저의 기록 중 `diaryid`에 해당하는 소감을 상세 조회하는 uri
/users/{userId}/diarys | POST | 로그인한 유저가 소감을 작성하는 uri
/users/{userId}/diarys/{diaryId} | PUT | 이미 기록한 소감을 수정하는 uri
/users/{userId}/diarys/{diaryId} | DELETE | 이미 기록한 소감을 삭제하는 uri

### CultureInfo (R)

URL | 요청 | 설명
:--: | :--: | :--:
/cultureInfos | GET | 문화생활 전체 목록 조회
/cultureInfos?category={category} | GET | 카테고리에 맞는 문화생활 조회
/cultureInfos?find={query} | GET | 검색어`query`에 맞는 문화생활 조회
/cultureInfos/{cultureinfoId} | GET | 문화생활 상세조회

