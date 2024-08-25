# kafka + zookeeper

## P1: kafkaProd + MainSvc : 8080 (:8080 -> :9092 참조) 
## P2: kafkaConsumer + NotifySvc + dataParsed : 9292
## P3: kafkaCluster : Zookeeper(:2181) + kafka (:9092)
## P4: react : nginx 80:80 by ProxySvc(:443 Proxy :8080)

### 
