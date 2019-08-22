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
- AWS EC2 & S3 bucket
- jenkins

## 주요 기능

1. 문화 생활 조회

   현재 또는 미래에 예정되있는 문화생활을 조회하는 기능

2. 소감 기록

   문화생활을 하고 난 뒤 간단한 소감 등을 기록하는 기능

3. 알림

   문화생활을 기록하는 것에 초점을 맞춰 사용자가 알림 받을 날을 설정하면 앱에서 해당 시간에 알림을 주는 기능

## RESTAPI 설계

### Jwt Token(R)
URL | 요청 | 설명
:--: | :--: | :--:
/signInOrUp?snsName={kakao, facebook} | POST | JWT TOKEN 생성/재생성

### User (CRUD)

URL | 요청 | 설명
:--: | :--: | :--:
/users | GET | 마이페이지
/users/{userid} | PUT | 회원 수정
/users/{userid} | DELETE | 회원 탈퇴

### Diary (CRUD)

URL | 요청 | 설명
:--: | :--: | :--:
/diarys | GET |  유저의 모든 소감을 조회하는 uri
/diarys/{diaryId} | GET |  유저의 기록 중 `diaryid`에 해당하는 소감을 상세 조회하는 uri
/diarys | POST | 로그인한 유저가 소감을 작성하는 uri
/diarys/upload/image | POST | `diary`를 생성하기 전 image를 먼저 s3에 올리는 uri 
/diarys/{diaryId} | PUT | 이미 기록한 소감을 수정하는 uri
/diarys/{diaryId} | DELETE | 이미 기록한 소감을 삭제하는 uri
/diarys/{diaryId}/like | GET | `diaryId`에 해당하는 기록에 좋아요 표시하는 uri 

### CultureInfo (R)

URL | 요청 | 설명
:--: | :--: | :--:
/cultureInfos?category={category}&sort={sort}&page={page} | GET |  문화생활 전체 목록 조회(최신순(new), 인기순(popular), 기본값: 최신순(new)), 카테고리에 맞는 문화생활 조회
/cultureInfos/search?query={query} | GET | 검색어`query`에 맞는 제목 조회
/cultureInfos/title?title={title} | GET | 검색어`title`에 맞는 문화생활 조회
/cultureInfos/id/{cultureInfoId} | GET | 문화생활 상세조회

