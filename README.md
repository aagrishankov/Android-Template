# Android-Template
Template Android Application

### **Мультимодульная архитектура с API и IMPL**

### **Основные концепции**

1. **API-модуль:**
    - **Содержит:** Интерфейсы, контракты, абстракции и модели данных, необходимые для взаимодействия с этой фичей.
    - **Цель:** Обеспечить интерфейс для других модулей и слоев приложения без раскрытия деталей реализации.
    - **Примеры содержимого:** Интерфейсы репозиториев, сервисов, моделей данных и других контрактов.
2. **IMPL-модуль:**
    - **Содержит:** Конкретные реализации интерфейсов и абстракций, а также всю логику работы фичи.
    - **Цель:** Реализовать функциональность фичи, инкапсулируя все детали реализации внутри этого модуля.
    - **Примеры содержимого:** Реализация репозиториев, сервисов, use-case'ов, конкретные модели данных и вспомогательные классы.

### **Преимущества**

1. **Изоляция:** Четкое разделение интерфейсов и реализаций позволяет изолировать изменения в реализации от других частей приложения.
2. **Тестируемость:** Легче создавать mock-объекты для тестирования, так как зависимости описаны через интерфейсы.
3. **Модульность:** Улучшенная модульность и возможность повторного использования кода.
4. **Ясность:** Ясное разделение обязанностей между контрактами и их реализациями.

  

```Markdown
project-root
│
├── feature-1
│   ├── api
│   │   └── build.gradle
│   └── impl
│       └── build.gradle
│
├── feature-2
│   ├── api
│   │   └── build.gradle
│   └── impl
│       └── build.gradle
│
└── build.gradle
```

  

### **Важные моменты**

1. **Модули API:**
    - **Содержат только интерфейсы, абстракции и модели данных.**
    - **Не содержат реализаций, только контракты.**
2. **Модули IMPL:**
    - **Содержат конкретные реализации интерфейсов из API-модулей.**
    - **Могут включать зависимости для работы с сетью, базой данных и другими сервисами.**
    - **Имеют собственные внутренние модели, мапперы и утилиты.**

### **Пример зависимости между модулями**

IMPL-модуль зависит от API-модуля, но не наоборот. Это обеспечивает изоляцию и упрощает тестирование и замену реализаций.


Архитектуре с использованием стеков, таких как Kotlin, Jetpack Compose, Ktor, Koin, MVI, Room и Clean Architecture.

  

```Markdown
project-root
│
├── feature
│   ├── api
│   │   └── build.gradle.kts
│   │   └── src
│   │       └── main
│   │           └── kotlin/com/example/feature/api
│   │               ├── [models]
│   │               │   └── Model.kt
│   │               ├── [repositories]
│   │               │   └── Repository.kt
│   │               ├── [widgets]
│   │               │   └── ComposeWidget.kt
│   │               └── navigation
│   │                   └── Navigation.kt
│   └── impl
│       └── build.gradle.kts
│       └── src
│           └── main
│               └── kotlin/com/example/feature/impl
│                   ├── [di]
│                   │   └── DiModule.kt
│                   ├── [mappers]
│                   │   └── Mapper.kt
│                   ├── [network]
│                   │   ├── Api.kt
│                   │   └── models
│                   │       └── Dto.kt
│                   ├── [repositories]
│                   │   └── RepositoryImpl.kt
│                   ├── [paging]
│                   │   ├── Pagination.kt
│                   │   └── Mediator.kt
│                   ├── [database]
│                   │   ├── Dao.kt
│                   │   ├── Database.kt
│                   │   └── models
│                   │       └── Entity.kt
│                   ├── [interactors]
│                   │   └── Interactor.kt
│                   ├── [useCases]
│                   │   └── UseCaseImpl.kt
│                   ├── [widgets]
│                   │   └── ComposeWidgetImpl.kt
│                   └── screens
│                       ├── screen1
│                       │   ├── [mvi]
│                       │	  │   ├── Handler1.kt
│                       │	  │   └── Store1.kt
│                       │   ├── [views]
│                       │	  │   ├── View1.kt
│                      	│   │   └── View2.kt
│                       │   ├── Screen1.kt
│                       └── [screen2]
│                           └── [views]
└── build.gradle.kts
```

Иерархические элементы выделенные квадратными скобками, например `[database]`, могут быть исключены, если не требуется реализация в данной фиче.

  

### **Практические советы**

1. **Инкапсуляция:** Старайтесь держать реализацию как можно более инкапсулированной в IMPL-модуле, предоставляя только необходимый API-модуль.
2. **Слабые зависимости:** Ссылки между модулями должны быть как можно более слабыми, используя интерфейсы и абстракции.
3. **Тесты:** Пишите юнит-тесты для API-модуля и интеграционные тесты для IMPL-модуля.
4. **Рефакторинг:** Регулярно пересматривайте архитектуру и структуру модулей, чтобы убедиться, что они остаются эффективными и легко поддерживаемыми.

  

Эта структура помогает создать хорошо организованный, легко поддерживаемый и масштабируемый проект, который следует принципам чистой архитектуры. Каждый модуль имеет свою четко определенную ответственность, что делает проект более модульным и тестируемым.
