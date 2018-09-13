# Тестовое задание

Для запуска необходимо:

1. Загрузить в базу данных PostgreSQL дамп dump.txt, который находится в [релизах](https://github.com/dvarubla/spring_test_task/releases)
2. Запустить программу таким образом: `java -jar test_task-1.0-shaded.jar --port порт_приложения --db сервер_базы порт_базы имя_базы пользователь пароль`, например,  `java -jar test_task-1.0-shaded.jar --port 16363 --db localhost 5434 test_db postgres 12345` Для запуска требуется Java 8.