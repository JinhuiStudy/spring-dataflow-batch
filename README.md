# spring-batch

### Notion: https://highfalutin-giant-304.notion.site/Spring-Batch-be9c44077c59426d82d8c17c0efc4b9a
### Spring-Data-Flow: https://dataflow.spring.io/docs

### 참고 링크 
#### 1. data Flow Ref
##### https://dataflow.spring.io/docs/batch-developer-guides/batch/data-flow-simple-task/https://dataflow.spring.io/docs/batch-developer-guides/batch/data-flow-simple-task/

#### 2. Youtube
##### https://www.youtube.com/watch?v=awIHq5CyxPg



[//]: # (Exception trying to launch [AppDeploymentRequest@92332ca commandlineArguments = list['--spring.cloud.task.executionid=34'], deploymentProperties = map[[empty]], definition = [AppDefinition@653fbc8d name = 'billrun', properties = map['management.metrics.tags.service' -> 'task-application', 'spring.cloud.task.closecontextenabled' -> 'true', 'spring.datasource.username' -> 'root', 'spring.datasource.url' -> 'jdbc:mysql://mysql:3306/dataflow', 'spring.datasource.driverClassName' -> 'org.mariadb.jdbc.Driver', 'management.metrics.tags.application' -> '${spring.cloud.task.name:unknown}-${spring.cloud.task.executionid:unknown}', 'spring.cloud.task.name' -> 'billrun', 'spring.datasource.password' -> 'rootpw']], resource = Docker Resource [docker:parkjinhui/batch-service:0.0.1]])


##### ./mvnw billstep:clean billstep:install -DskipTests
##### ./mvnw -Dmaven.repo.local=/Users/jinhuipark/.m2/repository clean install