Алгоритм прохождения экзамена:
1. Инициализация заваисимостей 
   - spring web
   - spring security
   - thymeleaf
   - validation
   - spring devtools
   - h2 database
   - lombok
2. Конфигурация
   - Изменить название application.properties на application.yml
   - Написать конфиг: `
     spring:
       main:
         banner-mode: off
       datasource:
         username: Paul1234
         password: password123
         url: jdbc:h2:file:./database/data
       jpa:
         hibernate.ddl-auto: update
         open-in-view: false
         show-sql: true
   `
3. Создать роли пользователей в корневой директории рядом со страртером приложения и реализовать интерфейс 
   GrantedAuthority
4. Рядом с ролями добавить класс UserPrincipal реализующий интерфейс UserDetails
5. Проектирование сущностей БД в файле *HELP.md*
   - Схема каждой сущности
   - Определить отношения между таблицами
   - Определить размер каждого из полей, например может понадобиться вместо обычного varchar, текстовое поле - text,
     которое длиннее во много раз
6. Реализация классов сущностей внутри каталога Model, в том числе UserEntity
7. Написание репозиториев в директории Repository
8. Создать класс кофигурации безопасности
9. Добавить html-файл с фрагментами приложения
   - Добавить в тег <html два аттрибута: `
     xmlns:th="http://www.thymeleaf.org"
     xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity6"
   ` 
   - Редактировать содержимое тега <title 
   - Добавить базовую стилизацию <link rel="stylesheet" href="https://cdn.simplecss.org/simple.css">
   - Шапка сайта header > nav > a
10. Создать пакет Dto
    - Валидатор для проверки уникальности поля (если есть в требовании)
    - UserDto
    - ... *Dto
11. Корневая страница для проверки работы и контроллер
12. Сделать вход и регистрацию
13. Страницы редактирования сущностей с контроллерами

Ремарка:
1. Вся логика ДОЛЖНА быть в контроллере
2. Контроллер можно инъецировать в другой
3. Формы редактирования и добавления сущностей должны содержать <input th:field="*{id}" type="hidden" 