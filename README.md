# [musinsa] Java Backend 과제
## 1. 구현범위
* 구현 1) 카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API
  * 조회 URL 정보는 아래와 같습니다.
    * http://localhost:8080/api/v1/codi/category/best-items ( method : get )
    * Response
      * ```
        {
          "categories": [
              {
                  "categoryName": "상의",
                  "brandName": "J",
                  "priceNumber": "400",
                  "price": 400
              },
              {
                  "categoryName": "아우터",
                  "brandName": "J",
                  "priceNumber": "500",
                  "price": 500
              },
              {
                  "categoryName": "바지",
                  "brandName": "J",
                  "priceNumber": "700",
                  "price": 700
              },
              {
                  "categoryName": "스니커즈",
                  "brandName": "J",
                  "priceNumber": "800",
                  "price": 800
              },
              {
                  "categoryName": "가방",
                  "brandName": "J",
                  "priceNumber": "700",
                  "price": 700
              },
              {
                  "categoryName": "모자",
                  "brandName": "J",
                  "priceNumber": "300",
                  "price": 300
              },
              {
                  "categoryName": "양말",
                  "brandName": "J",
                  "priceNumber": "600",
                  "price": 600
              },
              {
                  "categoryName": "액세서리",
                  "brandName": "J",
                  "priceNumber": "500",
                  "price": 500
              }
          ],
          "totalPrice": "4,500"
          }
        ```
* 구현 2) 단일 브랜드로 모든 카테고리 상품을 구매할 때 최저 가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API
  * 조회 URL 정보는 아래와 같습니다.
    * http://localhost:8080/api/v1/codi/category/lowest ( method : get )
    * Response
    * ```
      {
        "최저가": {
           "브랜드": "D",
           "카테고리": [
            {
              "카테고리": "상의",
              "가격": "10,100"
            },
            {
              "카테고리": "아우터",
              "가격": "5,100"
            },
            {
              "카테고리": "바지",
              "가격": "3,000"
            },
            {
              "카테고리": "스니커즈",
              "가격": "9,500"
            },
            {
              "카테고리": "가방",
              "가격": "2,500"
            },
            {
              "카테고리": "모자",
              "가격": "1,500"
            },
            {
              "카테고리": "양말",
              "가격": "2,400"
            },
            {
              "카테고리": "액세서리",
              "가격": "2,000"
            }
         ],
        "총액": "36,100"
        }
      }
      ```
* 구현 3) 카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API
  * 조회 URL 정보는 아래와 같습니다.
    * http://localhost:8080/api/v1/codi/category/{categoryName} ( method : get )
      * {categoryName} 은 '상의', '가방', '양말'... 등 명칭으로 확인할 수 있습니다.
      * Response
      * ```
        {
          "카테고리": "상의",
          "최저가": [
            {
              "브랜드": "C",
              "가격": "10,000"
            }
          ],
          "최고가": [
            {
              "브랜드": "I",
              "가격": "11,400"
            }
          ]
        }
        ```
  * 구현 4) 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
    1. 추가 URL 정보는 아래와 같습니다.
       * http://localhost:8080/api/v1/codi/create ( method : post )
         * 파라미터 정보는 아래와 같습니다.
           * ```
             {
                 "brand":"J",
                 "top":400,
                 "outer":500,
                 "pants":700,
                 "sneakers":800,
                 "bag":700,
                 "hat":300,
                 "socks":600,
                 "accessories":500
             } 
             ```
    2. 수정 URL 정보는 아래와 같습니다.
        * http://localhost:8080/api/v1/codi/modify ( method : put )
          * 파라미터 정보는 아래와 같습니다.
            * ``` 
              {
                "brand":"J",
                "top":400,
                "outer":500,
                "pants":700,
                "sneakers":800,
                "bag":700,
                "hat":300,
                "socks":600,
                "accessories":500
              }
              ```
    3. 삭제 URL 정보는 아래와 같습니다.
        * http://localhost:8080/api/v1/codi/remove ( method : delete )
          * 파라미터 정보는 아래와 같습니다.
            *  ```
                {
                    "brand" : "I"
                }
               ```
## 2. 코드 빌드, 실행 방법
  * Git, Java는 설치되어 있다고 가정합니다.
  * 코드 빌드 및 실행은 아래 작성한 순서대로 진행하면 실행 가능합니다.
  * ```
    $ git clone https://github.com/ysyoo89/musinsa.git
    $ cd musinsa
    $ ./gradlew clean build -x test
    $ java -jar build/libs/musinsa-0.0.1-SNAPSHOT.jar
    ```
  * 접속 Base URI : `http://localhost:8080`

## 3. 개발 환경
* 기본 환경
  * IDE: Intellij IDEA Ultimate
  * OS : Windows 10
  * Git
* Server
  * Java 17
  * Spring boot 2.7.8
  * JPA
  * H2
  * Grable
  * Mapstruct
  * lombok
  * Junit5

## 4. 기타 정보
* 오류 시 아래와 같은 정보로 공통적으로 전달됩니다.
  * ```
    {
        "localDateTime": "2023-11-22T17:25:45.0789218",
        "status": 500,
        "error": "INTERNAL_SERVER_ERROR",
        "code": "REQUEST_DATA_NULL_POINT",
        "message": "데이터가 적합하지 않습니다."
    }
    ```