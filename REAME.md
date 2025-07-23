# Query サーバのみ起動テストステップ
bash```
docker compose down -v
docker compose up test_query_postgres -d
mvn flyway:migrate
mvn clean package
docker compsoe up -d --build
```