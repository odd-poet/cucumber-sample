@logic
Feature: 입력값의 검증

  시스템 사용자가
  시스템를 인터랙티브하게 사용할 수 있도록
  모든 입력이 완료되지 않은 상태에서도 입력 오류를 즉각 발견할 수 있어야 한다.

  - 입력 첫 라인에는 총 선수 수가 입력되며 유효한 총 선수 수는 2~100이다
  - 각 선수 정보의 포맷은 다음과 같다 : <선수번호> <싫어하는 선수수> <싫어하는 선수번호> <싫어하는 선수번호> ...
  - 선수ID로써의 선수번호는 (1~총 선수 수)의 범위를 갖는다

  Scenario Outline: 유효한 선수 총원
    Given 총원으로 <playerCount>가 입력된다
    Then 입력은 <valid>하다

  Examples:
    | playerCount | valid |
    | 2           | valid  |
    | 100         | valid  |
    | 5           | valid  |
    | -1          | invalid |
    | 101         | invalid |
    | 1           | invalid |

  Scenario Outline: 선수번호의 범위는 (1~총원)이어야 한다
    Given 총원이 '5'이고
    When <no>번 선수정보가 <playerInfo>로 입력된다
    Then 그 입력 선수정보는 <valid>하다

  Examples:
    | no | playerInfo | valid |
    | 1  | 1 1 2      | valid  |
    | 1  | A 1 B      | invalid |
    | 1  | 0 1 1      | invalid |
    | 5  | 5 1 3      | valid  |
    | 6  | 6 0        | invalid |
    | 3  | 3 1 8      | invalid |

  Scenario Outline: 선수는 자기 자신을 싫어할 수 없다
    Given 총원이 '5'이고
    When <no>번 선수정보가 <playerInfo>로 입력된다
    Then 그 입력 선수정보는 <valid>하다

  Examples:
    | no | playerInfo | valid |
    | 1  | 1 1 1      | invalid |
    | 1  | 1 2 3 4    | valid  |
    | 1  | 1 2 1 2    | invalid |

  Scenario Outline: 싫어하는 선수 수와 선수번호 갯수는 일치해야한다
    Given 총원이 '5'이고
    When <no>번 선수정보가 <playerInfo>로 입력된다
    Then 그 입력 선수정보는 <valid>하다

  Examples:
    | no | playerInfo | valid |
    | 1  | 1 1 2      | valid  |
    | 1  | 1 2 2      | invalid |
    | 1  | 1 0 0      | invalid |
    | 1  | 1 1        | invalid |
    | 1  | 1 0        | valid  |


  Scenario Outline: 싫어하는 선수가 한 라인에 두번 나올 수 없다
    Given 총원이 '5'이고
    When <no>번 선수정보가 <playerInfo>로 입력된다
    Then 그 입력 선수정보는 <valid>하다

  Examples:
    | no | playerInfo | valid |
    | 1  | 1 2 2 3    | valid  |
    | 1  | 1 2 3 3    | invalid |
    | 1  | 1 3 2 3 4  | valid  |
    | 1  | 1 3 2 4 4  | invalid |




