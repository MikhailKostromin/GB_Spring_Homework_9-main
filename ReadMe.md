Базовое задание:
Добавить в один из Ваших проектов сделанных ранее ApiGateWay и Eureka.\
В проекте обязательно должна быть Spring Data.


Возможно при клонировании репозитория не будет модулей. Их надо подключть вручную:\
Зайти в File -> Project Structure -> Modules -> + -> Import Module\
Далее выбираем любой pom.xml\
Можно выбрать там же все модули,\
а можно добавлять модули в вкладке справа Maven -> + -> pom.xml

Eurika server запускаются на порту:
http://localhost:8761

## Запуск из конфигурации "run all" в idea
Запустятся 4 модуля.

API микросервисов:

Главная страница (get запрос):  http://localhost:8080/shop/

Переход по товарам (get запрос): http://localhost:8080/shop/product/1

Покупка товара (post запрос): http://localhost:8080/shop/buyProduct
