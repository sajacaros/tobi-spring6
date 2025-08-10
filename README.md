# 토비의 스프링 6 - 이해와 원리
## tool 설치
### choco 설치
  - [choco 사이트 이동](https://chocolatey.org/install)
  - 스크립트 power shell 관리자 모드에서 실행
### httpie 설치
  - choco install httpie
  - http -v https://open.er-api.com/v6/latest/USD
## Week1
### PaymentService 개발
### 요구사항
- 해외직구를 위한 원화 결제 준비 기능 개발
- 주문번호, 외국 통화 종류, 외국 통화 기준 결제 금액을 전달 받아서 다음의 정보를 더해 Payment 를 생성
  - 적용 환율
  - 원화 환산 금액
  - 원화 환산 금액 유효시간
- PaymentService.prepare() 메서드로 개발
  - Payment 오브젝트 리턴
### 개발방법
- 빠르게 완성해서 가장 간단한 방법 찾기
- 작성한 코드가 동작하는지 확인하는 방법 찾기
- 조금씩 기능 추가하고 검증
- 코드를 한눈에 이해하기 힘들다면 코멘트로 설명 달기
### 환율 가져오기
- https://open.er-api.com/v6/latest/{기준통화} 이용
  - 이 서비스가 더이상 유지되지 않는 경우 사용할 다른 서비스 URL을 강의자료에서 확인
- JSON 포맷으로 리턴되는 값을 분석해서 원화(KRW) 환율 값을 가져오기
- JSON을 자바 오브젝트로 변환
  - Jackson 프로젝트의 ObjectMapper 사용