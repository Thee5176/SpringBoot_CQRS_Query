# SpringBoot_CQRS_Query

## Table Of Content
1. [Run Process](#run-process)
2. [Development Process](#development-process)
3. [Design Document](#design-document)
4. [Highlight Functionality](#highlight-functionality)

## Run Process with Docker
```bash
#1 Prepare directory and clone from github
git clone 
cd SpringBoot_CQRS_Query

#2 

#3  Build package and run process with docker
mvn clean package
docker compose up -d --build

```
Check the running project at [port 8184](http://localhost:8184)

## Development Process
see [Main Repository](https://github.com/Thee5176/Accounting_CQRS_Project)

# Highlight Functionality

## Springboot Query Service

| Feature                     | Description                                                                                                    | Reference Link                                                                                                                               |
|-----------------------------|----------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------|
| Join Table Query with JOOQ  | Tackle N+1 Problem in Repository Layer using JOIN query                                                       | [LedgersRepository.java](https://github.com/Thee5176/springboot_cqrs_query/blob/develop/src/main/java/com/thee5176/ledger_query/Infrastructure/repository/LedgersRepository.java#L57) |
| Flatten Data Extraction     | Tackle N+1 Problem in Service Layer by creating Map of Id to Entity (removes recursive querying)               | [LedgersQueryService.java](https://github.com/Thee5176/SpringBoot_CQRS_Query/blob/develop/src/main/java/com/thee5176/ledger_query/Domain/service/LedgersQueryService.java#L24)       |

### Join Table Query with JOOQ - Sequence Diagram
```mermaid
sequenceDiagram
    participant Service
    participant JOOQContext
    participant LedgersTable
    participant LedgerItemsTable

    Service->>JOOQContext: fetchDtoContext()
    JOOQContext->>LedgersTable: from(Tables.LEDGERS)
    JOOQContext->>LedgerItemsTable: leftJoin(Tables.LEDGER_ITEMS)
    JOOQContext->>LedgerItemsTable: on(LEDGERS.ID = LEDGER_ITEMS.LEDGER_ID)
    JOOQContext->>LedgersTable: where(LEDGERS.ID = id)
    JOOQContext->>Service: fetchInto(LedgersQueryOutput.class)
    Service-->>Service: return List<LedgersQueryOutput>
```

### Flatten Data Extraction Transaction - Sequence Diagram

```mermaid
sequenceDiagram
    participant Controller
    participant LedgersRepository
    participant ModelMapper
    participant Logger

    Controller->>LedgersRepository: getAllLedgersDTO()
    LedgersRepository-->>Controller: List<LedgersQueryOutput>
    Controller->>Logger: log.info(queryOutputs)
    Controller->>ModelMapper: map each LedgersQueryOutput to GetLedgerResponse
    ModelMapper-->>Controller: List<GetLedgerResponse>
    Controller->>ModelMapper: map and group by ledgerId to LedgerItemsAggregate
    ModelMapper-->>Controller: Map<ledgerId, List<LedgerItemsAggregate>>
    Controller->>Controller: setLedgerItems() for each GetLedgerResponse
    Controller-->>Controller: return List<GetLedgerResponse>
```

## Test Result:
API Test Case:
  ✔ RestAPI Controller
  <img width="896" height="711" alt="image" src="https://github.com/user-attachments/assets/c36ab702-d6f2-49e9-a930-78f58042808a" />
