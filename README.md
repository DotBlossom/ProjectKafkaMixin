
## P1: kafkaProd + MainSvc : 8080 (:8080 -> :9092 참조) 
## P2: kafkaConsumer + NotifySvc + dataParsed : 9292
## P3: kafkaCluster : Zookeeper(:2181) + kafka (:9092)
## P4: react : nginx 80:80 by ProxySvc(:443 Proxy :8080)


## 추가된 코드들, 기존 코드 변경 x 
### 테스트 위해 entity만 살짝 바꿨습니다
```
src/main/java/com/delta/delta/aop/ExportToKafkaProducerAspect.java
- 메인 서비스에서 발생되는 이벤트를 받고, 카프카 프로듀서로 데이터 전달 및 조절

src/main/java/com/delta/delta/configuration/KafkaProducerConfig.java
- 프로듀서 기본 설정

src/main/java/com/delta/delta/controller/NotificationsEventController.java
- 컨트롤러(안 씀, 혹시 나중에 필요할까봐 잠시 냅뒀습니다)

src/main/java/com/delta/delta/dto/NotificationsDto.java
(데이터 전달용)
```

