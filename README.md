# MyCarsRestApi
Задание:
Разработать REST API на Spring для управления списком автомобилей.
Автомобиль имеет следующие атрибуты: model, maxSpeed, mileage.
В качестве СУБД использовать PostgreSQL.
API должно позволять выполнять все CRUD операции над списком.
Приветствуется использование Docker Compose.
Решение разместить на GitHub и прислать ссылку.
Доп. задание: реализовать постраничный вывод (пагинацию) и возможность в запросе устанавливать фильтрацию по атрибутам.

Пояснения к решению:
После запуска REST API доступно по адресу http://localhost:8080/cars (через Docker порт будет 8081)
Для проверки API можно запускать JS скрипты в консоли браузера Chrome:
Создание
fetch('/cars', { method: 'POST', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ model: "Ford", maxSpeed: 190, mileage: 33000 })}).then(console.log)
Изменение
fetch('/cars/1', { method: 'PUT', headers: {'Content-Type': 'application/json'}, body: JSON.stringify({ model: "Hyundai", maxSpeed: 175, mileage: 55000 })}).then(console.log)
Удаление
fetch('/cars/2', { method: 'DELETE'}).then(console.log)

Вывод данных выполняется постранично, управляется это параметрами page (индекс страницы) и size (число записей)
например, /cars?page=1&size=5 (без параметров page=0 и size=3)
Если запросить с size=0, то вернутся все записи в одной странице

Можно задать фильтр по атрибуту model, например, /cars?model=Ford

Docker образ приложения Docker Hub: bolotovmu/mycarsrestapi_app
Связку приложения и БД PostgreSQL можно собрать по файлу: docker-compose.yml командой docker-compose up --build