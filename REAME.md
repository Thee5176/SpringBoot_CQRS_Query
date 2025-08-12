# Query サーバのみ起動テストステップ
bash```
docker compose down -v
docker compose up test_query_postgres -d
./mvnw flyway:migrate
./mvnw clean package
docker compose up -d --build
```