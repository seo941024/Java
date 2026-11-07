# basics · 기본 문법

변수와 자료형, 형 변환, 연산자, 조건문, Scanner 입력을 다룬 콘솔 프로그램 모음. [ex00/src/ex00](../ex00/src/ex00) 패키지와 같은 구성이며 파일마다 `package ex00` 선언.

## 다룬 개념

| 개념 | 뜻과 사용 방법 |
|---|---|
| 변수와 자료형 | 값을 담는 이름 붙은 저장 공간과 그 값의 종류. `int`, `double`, `char`, `boolean`, `String` 변수 선언, `final`로 상수 `PI` 선언, `var`로 자료형 추론 |
| 메소드 | 이름을 붙여 다시 호출할 수 있게 만든 코드 묶음. `static int sum(int n, int m)`을 정의하고 `main()`에서 호출 |
| 형 변환 | 값을 다른 자료형으로 바꾸는 일. 작은 자료형에서 큰 자료형으로는 자동 변환, `(int)`, `(byte)`처럼 앞에 자료형을 적으면 강제 변환. 강제 변환 위치에 따라 결과가 달라지는 경우 비교 |
| 산술 연산자 | `+ - * / %`. 정수끼리 나누면 몫만 남고 `%`는 나머지. 초를 시·분·초로, 금액을 지폐·동전 개수로 나누는 계산에 사용 |
| 대입·증감 연산자 | `+=`, `*=`, `%=`는 계산 후 대입. `d++`는 증가 전 값을, `++d`는 증가 후 값을 식의 결과로 사용 |
| 비교·논리 연산자 | 비교 결과는 `true` 또는 `false`. `&&`는 둘 다 참, `\|\|`는 하나라도 참, `^`는 서로 다를 때 참, `!`는 반대 |
| 비트·시프트 연산자 | 정수를 2진수 비트 단위로 계산하는 연산자. `&`, `\|`, `^`, `~`와 `<<`, `>>`, `>>>`. `>>`는 부호 비트 유지, `>>>`는 빈 자리를 0으로 채움 |
| 문자열 연결 | `+` 연산은 왼쪽부터 계산되어 숫자끼리는 더하고 문자열을 만난 뒤부터는 이어 붙임. `1 + 2 + "3" + 4 + 5`의 결과는 `"3345"` |
| if-else 문 | 조건이 참일 때와 거짓일 때 실행할 문장을 나누는 분기문. `else if`로 점수 구간을 여러 단계로 나누고, if 안에 if를 둔 중첩 if로 학년별 기준 분리 |
| switch 문 | 값에 맞는 `case`로 이동하는 분기문. `break`가 없으면 다음 case로 이어지는 성질을 이용해 여러 값을 한 묶음으로 처리. 정수와 문자열 모두 분기 기준으로 사용 |
| Scanner 입력 | 키보드 입력을 읽는 `java.util.Scanner`. `next()` 문자열, `nextInt()` 정수, `nextDouble()` 실수, `nextBoolean()` 논리값, 사용 후 `close()` |
| 입력 검증 반복 | `while(true)` 안에서 범위를 벗어난 값이면 `continue`로 다시 입력받고, 처리가 끝나면 `break`로 반복 종료 |

## 파일별 내용

| 파일 | 내용 | 핵심 코드 |
|---|---|---|
| [Hello.java](Hello.java) | 정수 합 메소드 호출 결과와 문자, 문자열, `var`로 선언한 실수 출력 | `static int sum(int n, int m)`, `var PI = 3.14` |
| [TypeConversion.java](TypeConversion.java) | 자료형이 섞인 연산과 강제 형 변환 위치에 따른 결과 차이 출력 | `(byte)(b+i)`, `10/4`와 `10.0/4`, `(int)2.9+1.8`과 `(int)(2.9+1.8)` |
| [testmath.java](testmath.java) | 숫자와 문자열이 섞인 `+` 연산의 계산 순서 확인 | `1 + 2 + "3" + 4 + 5` |
| [ArithmeticOperator.java](ArithmeticOperator.java) | 입력한 초를 시간, 분, 초로 변환 | `time % 60`, `(time / 60) % 60`, `(time / 60) / 60` |
| [AssignmentIncDecOperator.java](AssignmentIncDecOperator.java) | 복합 대입 연산 결과와 전위·후위 증감 연산 결과 비교 | `a+=3`, `c%=2`, `a=d++`, `a=++d` |
| [LogicalOperator.java](LogicalOperator.java) | 문자·정수·실수 비교 결과와 논리 연산 결과 출력 | `'a'>'b'`, `&&`, `\|\|`, `^`, `!(3!=2)` |
| [BitOperator.java](BitOperator.java) | `short` 값의 비트 연산 결과를 16진수 네 자리로 출력, `byte` 값의 시프트 연산 결과 출력 | `printf("%04x\n", (short)(a&b))`, `c<<2`, `d>>2`, `d>>>2` |
| [CircleArea.java](CircleArea.java) | 원의 면적 계산. 반지름 제곱과 원주율을 각각 `int`로 바꿔 소수점이 버려진 값 출력, 실수 계산식은 주석으로 보존 | `final double PI`, `(int)(radius*radius)*(int)PI` |
| [ChangeCalc.java](ChangeCalc.java) | 입력 금액을 5만원권부터 일원까지 단위별 개수로 분해 | `change/50000`, `change = change % 50000` 반복 |
| [ExchangeD.java](ExchangeD.java) | 원화를 입력받아 1달러 1200원 기준 달러로 환산 | `(double)number/1200` |
| [MultipleOfThree.java](MultipleOfThree.java) | 입력한 수의 3의 배수 여부 판별 | `number % 3==0` |
| [SuccessOrFail.java](SuccessOrFail.java) | 0부터 100 사이 점수만 받아 80점 이상 합격 판정 | `score > 100 \|\| score < 0`, `continue` |
| [Grading.java](Grading.java) | 범위 밖 점수는 다시 입력받고, if-else 다중 분기로 A부터 F까지 학점 계산 | `while(true)`, `else if(score >= 80)` |
| [GradingSwitch.java](GradingSwitch.java) | 같은 학점 계산을 switch 문으로 구현, 100점과 90점대를 한 case 묶음으로 처리 | `switch (score/10)`, `case 10: case 9:` |
| [NestedIf.java](NestedIf.java) | 점수와 학년을 입력받아 4학년은 70점 이상, 나머지 학년은 60점 이상 합격 판정 | 중첩 `if`, `year != 4` |
| [Season.java](Season.java) | 입력한 달의 계절 출력, 세 달씩 case 묶음 | `case 3 : case 4 : case 5 :`, `default` |
| [CoffeePrice.java](CoffeePrice.java) | 커피 이름 문자열로 가격 결정, 카푸치노와 카페라떼는 같은 가격 | `switch (order)`, `case "카푸치노": case "카페라떼":` |
| [ScannerEx.java](ScannerEx.java) | 이름, 도시, 나이, 체중, 독신 여부를 빈칸으로 구분한 한 줄 입력으로 읽기 | `next()`, `nextInt()`, `nextDouble()`, `nextBoolean()` |
| [PointInSquare.java](PointInSquare.java) | x와 y가 모두 100 이상 200 이하인 사각형 영역 안에 점이 있는지 판별 | `x >= 100 && x <= 200 && y >= 100 && y <= 200` |
| [CirclePoint.java](CirclePoint.java) | 원의 중심, 반지름, 점 좌표를 입력받아 점이 원 안, 경계, 바깥 중 어디인지 판별 | `(x-a)*(x-a)+(y-b)*(y-b) < r*r` |
| [Triangle.java](Triangle.java) | 세 정수가 모두 양수이고 두 변의 합이 나머지 한 변보다 클 때 삼각형 성립 판정 | `a+b>c && a+c>b && b+c>a` |

## ex00/src/ex00과 차이

| 파일 | basics | ex00/src/ex00 |
|---|---|---|
| ExchangeD.java | `int`로 입력받아 `+` 연결로 결과 출력 | `double` 변수에 저장, `printf("%.0f원은 $%.2f입니다.")` 출력 줄 추가 |
| Season.java | case 라벨을 한 줄에 하나씩 배치 | 같은 계절의 case 라벨을 한 줄에 배치 |
| ChangeCalc.java | 마지막에 `change % 1` 계산 줄 추가 | 해당 줄 없음 |
| CirclePoint.java | 판별 코드 동일 | 거리 계산식 메모 주석 한 줄 추가 |

나머지 파일은 내용 동일.
