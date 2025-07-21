# Query サーバのみ起動テストステップ
bash```
docker compose down -v
docker compose up test_query_postgres -d
mvn flyway:migrate
mvn clean package -DskipTests               #DBコンテナ起動していないと、総合テスト実行できない。 (ModelMapperTest)
docker compsoe up -d --build
```