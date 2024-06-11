# Async Paging

페이징 **`카운트`**, **`데이터 페치`** 쿼리 분리 예제. 

<br/><br/><br/><br/>

## 💻 프로그램 실행

빌드 후, 프로젝트 실행 합니다. 애플리케이션 실행 후, 데이터 초기화 작업이 있기 때문에 잠깐 대기합니다. 

```shell
./gradlew build
```

```shell
java -jar build/libs/paging-async-0.0.1.jar
```

<br/><br/><br/><br/>

## 📚 Content

페이징에서 **`카운트`** 와 **`데이터 페칭`** 두 쿼리가 순차적으로 실행됩니다. 이를 분리한 후, 비동기로 조회하면 조회 성능을 조금 더 향상시킬 수 있습니다. 

```kotlin
@Repository
class UserEntityReadRepository(
    private val queryFactory: JPAQueryFactory,
) : UserReadRepository {

    companion object {
        private val totalCountExpression = numberTemplate(Long::class.java, "count(1)")
    }

    override suspend fun findUsers(
        page: Int,
        size: Int,
    ): Pair<Long, List<User>> = coroutineScope {
        val totalCount = async {
            queryFactory.select(totalCountExpression)
                .from(user)
                .fetchOne() ?: 0L
        }

        val findUsers = async {
            queryFactory.selectFrom(user)
                .offset((page) * size.toLong())
                .limit(10)
                .fetch()
        }
        Pair(totalCount.await(), findUsers.await())
    }
}
```

> 단, 이는 offset 방식의 한계는 벗어나지 못하므로, 데이터가 많을 수록 속도가 느려집니다. 
