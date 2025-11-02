# Информационная безопасность - работа 1

Стек: Java + Spring Boot

## API

### `POST /auth/login`

Аутентификация пользователя по имени и паролю.
После успешной аутентификации возвращает JWT в теле ответа.

Пример тела запроса
```json
{
  "username": "username",
  "password": "qwerty12345"
}
```

Пример тела ответа
```json
{
  "username": "username",
  "accessToken": "<jwt>"
}
```

### `POST /auth/register`

Регистрация пользователя по имени и паролю.
После успешной регистрации возвращает JWT в теле ответа.

Пример тела запроса
```json
{
  "username": "username",
  "password": "qwerty12345"
}
```

Пример тела ответа
```json
{
  "username": "username",
  "accessToken": "<jwt>"
}
```

### `GET /api/data`

Получение данных.
Для доступа требуется наличие заголовка `Authorization` с валидным JWT.

Пример заголовка `Authorization`
```
Authorization: Bearer <jwt>
```

Пример тела ответа
```json
[
  {
    "id": 1,
    "name": "chapter1"
  },
  {
    "id": 2,
    "name": "chapter2"
  },
  {
    "id": 3,
    "name": "chapter3"
  }
]
```

### `POST /api/data`

Сохранение данных.
Для доступа требуется наличие заголовка `Authorization` с валидным JWT.

Пример заголовка `Authorization`
```
Authorization: Bearer <jwt>
```

Пример тела запроса
```json
{
  "name": "name"
}
```

Пример тела ответа
```json
{
  "id": 1,
  "name": "name"
}
```

## Базовые меры защиты

### Защита от SQLi (SQL-инъекций)

Используется ORM Hibernate под оберткой Spring Data JPA.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### Защита от XSS

Пользовательский ввод экранируется с помощью метода
`org.springframework.web.util.HtmlUtils.htmlEscape(input)`.

```java
public class XssUtils {

    public static String sanitize(String input) {
        return (input != null) ? HtmlUtils.htmlEscape(input) : null;
    }
}
```

### Защита от Broken Authentication

- Реализована выдача JWT при успешной аутентификации или регистрации
- Реализован фильтр запросов `JwtAuthenticationFilter` для проверки JWT в заголовке Authorization
- Сконфигурирована фильтрация запросов для путей `/api/**` с помощью реализованного фильтра
- Сконфигурировано хеширование паролей с помощью `BCryptPasswordEncoder`

## CI/CD

### Настройка CI/CD

Настроен workflow `ci.yml` для GitHub Actions,
запускающийся при каждом push или pull request в ветку main,
в котором происходит сборка проекта, запуск анализаторов и публикация отчетов.
Ссылки на опубликованные отчеты находятся в Summary пайплайна.
Также можно скачать архивы с отчетами из артефактов пайплайна.

### SAST (Static Application Security Testing)

Для статического анализа кода используется линтер `Spotbugs`.

Скриншот отчета

// TODO: вставить скриншот

### SCA (Software Composition Analysis)

Для проверки зависимостей на известные уязвимости используется плагин `OWASP Dependency-Check`.

Скриншот отчета

// TODO: вставить скриншот
