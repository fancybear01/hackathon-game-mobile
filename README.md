\# HackathonGame — документация проекта

Кратко: это Kotlin Multiplatform проект с UI на Compose Multiplatform, модульной архитектурой (feature api/impl), навигацией на Voyager, DI на Koin и шаблоном MVI для состояний/событий.

\- Платформы: Android, iOS
\- UI: JetBrains Compose Multiplatform (Material 3)
\- Архитектура: модульная, feature api/impl, MVI (intent → model → effect/event → view)
\- Навигация: Voyager
\- DI: Koin
\- Язык: Kotlin (JVM \+ Native)

\## Структура репозитория

Корневые файлы Gradle и общие настройки:
\- \`build.gradle.kts\`, \`settings.gradle.kts\`, \`gradle/\`, \`gradle.properties\`

Основные модули (извлечено по репозиторию):
\- \`composeApp/\` — Android/iOS entry для общего UI (Android Activity, iOS bridge)
\- \`iosApp/\` — iOS приложение (Xcode проект, SwiftUI обёртка, точка входа)
\- \`common/\` — общие вспомогательные библиотеки

- \`logger/\` — логгер
- \`mvi/\` — реализация MVI (general \+ koin-voyager связка)
- \`utils/\` — утилиты (androidMain/commonMain/iosMain)
  \- \`core/\` — базовые подсистемы
- \`network/\` — сетевой слой (заготовки)
- \`recources/\` — общий модуль ресурсов (Compose resources)
- \`settings/\` — настройки/хранилище
  \- \`components/\` — переиспользуемые доменные компоненты
- \`pet/\`, \`quiz/\` — доменные фичи-компоненты
  \- \`feature/\` — функциональные экраны (паттерн api/impl)
- \`main-screen/\` — главный экран (api, impl)
- \`course-screen/\` — экран курса (api, impl)
- \`quiz-screen/\` — викторина (api, impl)
- \`theory/\` — теория (api, impl)
- \`rewards-screen/\` — призы (api, impl)

Примечание: каждый feature-модуль имеет \`-api\` (контракты/точка навигации) и \`-impl\` (UI, MVI, DI-модуль Koin).

\## Технологии и ключевые решения

\- Compose Multiplatform (Material 3, TopAppBar, ModalBottomSheet)
\- Koin (koin-core, koin-compose) — DI контейнер, регистрация api/impl
\- Voyager — навигация между экранами через \`Screen\`/\`Navigator\`
\- MVI — собственные модули \`mvi\_general\`, \`mvi\_koin\_voyager\`:

- Action: пользовательские интенты
- Effect: изменения состояния
- Event: одноразовые события (навигация, тосты)
- State: модель UI
- Model (MviModel): обрабатывает Action → пушит Effect/Event

\## Навигационные сценарии (как сейчас настроено)

\- Главный экран

- Кнопка меню в TopBar открывает Drawer с пунктами: “Игры”, “Награды”, “Ежедневные задания”
- Нажатие “Игры” → переход к экрану курса
- Нажатие “Награды” → переход к экрану призов
- Нажатие “Ежедневные задания” → всплывающее окно (ModalBottomSheet) со списком мок-заданий
- Кнопка “ИЗУЧАТЬ” → переход к экрану курса
  \- Экран курса
- Нажатие на item с \`isTest = true\` → переход к экрану квиза (с передачей id теста)
- Нажатие на item с \`isTest = false\` → переход к экрану теории
  \- Экран квиза
- CenterAlignedTopAppBar со стрелкой “назад”, фон — вертикальный градиент, карточки вопросов/ответов
  \- Экран теории
- Приведён к стилю квиза: CenterAlignedTopAppBar со стрелкой, затемнённый фон, pager со статьями
  \- Экран призов
- Приведён к общему стилю: градиентный фон, TopAppBar со стрелкой, чип-переключатель “Полученные/Можно выиграть”, карточки призов

\## Где искать ключевой код (ссылки по путям)

Главный экран (Main):
\- UI: \`feature/main-screen/main-screen-impl/.../compose/MainScreenContent.kt\`
\- MVI: \`feature/main-screen/main-screen-impl/.../MainScreenModel.kt\`, \`mvi\` каталог
\- Экран: \`feature/main-screen/main-screen-impl/.../MainScreen.kt\`

Курс (Course):
\- UI: \`feature/course-screen/course-screen-impl/.../compose/CourseScreenContent.kt\`
\- Элементы списка: \`.../compose/components/SectionGroup.kt\`, \`SectionItem.kt\`
\- MVI/Экран: \`feature/course-screen/course-screen-impl/.../CourseScreenModel.kt\`, \`CourseScreen.kt\`

Квиз (Quiz):
\- UI: \`feature/quiz-screen/quiz-screen-impl/.../compose/QuizScreenContent.kt\`
\- MVI/Экран: \`feature/quiz-screen/quiz-screen-impl/...\`

Теория (Theory):
\- UI: \`feature/theory/theory-screen-impl/.../compose/TheoryScreenContent.kt\` (TopBar \+ pager)
\- Экран: \`feature/theory/theory-screen-impl/.../TheoryScreen.kt\`

Призы (Rewards):
\- UI: \`feature/rewards-screen/rewards-screen-impl/.../compose/RewardsScreenContent.kt\`
\- Список/карточки: \`RewardList.kt\`, \`RewardCard.kt\`
\- Экран (моки): \`feature/rewards-screen/rewards-screen-impl/.../RewardsScreen.kt\`

\## Моки данных

\- Ежедневные задания (показывается в BottomSheet с главного экрана):

- Файл: \`feature/main-screen/main-screen-impl/.../compose/MainScreenContent.kt\`
- Коллекция \`dailyTasks\`: список пар \`"Название" to isDone\`
- Как заменить: перенесите в отдельный репозиторий/UseCase и подключите через Koin

\- Призы (экран “Награды”):

- Файл: \`feature/rewards-screen/rewards-screen-impl/.../RewardsScreen.kt\`
- Списки \`wonRewards\` и \`availableRewards\` — реалистичные позиции (мерч, промокоды, монеты, бейджи)
- Как заменить: внедрите \`RewardsRepository\`, грузите данные из сети/локально, замените моки

\- Курс: структура секций/айтемов

- Модель: \`feature/course-screen/course-screen-impl/.../model/Item.kt\` (id, title, isTest, isCompleted)
- Переходы завязаны на \`isTest\`

\## Стайлгайд UI (фрагменты)

\- Градиенты фона: линейный \`#1919EF → #8694E4\` (экран/шиты) или вертикальный (квиз)
\- TopAppBar: прозрачный фон, белые иконки/заголовки, стрелка \`arrow\_2\`
\- BottomSheet: градиентный фон, скругление top-углов 16dp
\- Кнопка “ИЗУЧАТЬ”: градиент слева-направо (тот же набор цветов, что у дуги прогресса)
\- Дуга прогресса питомца: одна дуга, линейный градиент, старт с 12 часов, замкнутый круг (startAngle = -90, sweep по прогрессу)

\## Сборка и запуск

Предустановки:
\- JDK 21 (Gradle и Android compileOptions настроены на Java 21)
\- Android Studio Koala\+ (для Android-проекта), Android SDK/эмулятор/устройство
\- Xcode 15\+ (для iOS), CocoaPods при необходимости

Команды для Windows (cmd.exe):
\- Полная сборка (все модули):
\`\`\`bat
gradlew.bat clean build
\`\`\`
\- Android APK сборка (debug) из общего модуля UI:
\`\`\`bat
gradlew.bat :composeApp:assembleDebug
\`\`\`
\- Рекомендованный запуск: через Android Studio (Run на модуле composeApp)
\- iOS: открыть \`iosApp/iosApp.xcodeproj\` в Xcode и запустить на симулятор/устройстве

Если столкнулись с ошибками синхронизации:
\- Очистить кеш Gradle и пересобрать:
\`\`\`bat
gradlew.bat clean --stop
rmdir /s /q .gradle
gradlew.bat build
\`\`\`

\## Архитектура MVI — как это связано

Пример: клик “Игры” в Drawer главного экрана
\- View (\`MainScreenContent\`) вызывает \`onCourseClick()\`
\- View (\`MainScreen.kt\`) пушит \`MainScreenAction.ClickButtonOnCourse\`
\- Model (\`MainScreenModel.actor\`) эмитит \`MainScreenEvent.NavigateToCourseScreen\`
\- View ловит Event и делает \`navigator.push(courseScreenApi.courseScreen())\`

Пример: клик по пункту курса
\- Если \`item.isTest\` → \`CourseScreenAction.ClickOnQuiz(id)\` → Event \`NavigateToQuizScreen(id)\` → \`navigator.push(quizScreenApi.quizScreen(id))\`
\- Иначе → \`CourseScreenAction.ClickOnTheory(id)\` → Event \`NavigateToTheoryScreen\` → \`navigator.push(theoryScreenApi.theoryScreen())\`

\## DI и модульность

\- Каждая фича регистрирует свою API-реализацию в Koin-модуле \`\*Module.kt\` (например, \`RewardsScreenModule.kt\`)
\- Импорт модулей в корневой Koin делается в app слое (см. composeApp), после чего \`koinInject\<FeatureApi\>()\` доступен в экранах

\## Как добавить новую фичу (гайд)

1) Создайте модули \`feature/\<name\>-api\` и \`feature/\<name\>-impl\`
   \- \`\*-api\`: интерфейс \`\<Name\>ScreenApi \{ fun screen(...): Screen \}\`
   \- \`\*-impl\`: реализация экрана, MVI (Action/State/Effect/Event/Model), DI-модуль с \`single\<...Api\> \{ ...Impl() \}\`

2) Добавьте зависимости в нужные \`build.gradle.kts\` (\`implementation(projects.feature.\<name\>.\<name\>Api)\`)

3) Внедрите навигацию
   \- В месте-источнике перехода \`koinInject\<\<Name\>ScreenApi\>()\` и \`navigator.push(api.screen(...))\`

4) Покройте UI базовыми тестами/превью (по возможности)

\## Точки для кастомизации

\- Цвета/градиенты: централизуйте через свой \`Theme\`/\`AppColors\` модуль
\- Состояния BottomSheet: выведите в MVI, если нужна реакция на внешние события
\- Источники данных: замените локальные моки на репозитории \+ datasource (network/db)

\## Траблшутинг

\- Нет кнопки назад на экране: проверьте TopAppBar/\`onBackClick\` → должен вызывать \`navigator.pop()\`
\- BottomSheet не закрашивает нижнюю системную область: используйте прозрачный контейнер и задавайте фон на контент; системные insets учитываются библиотекой Material3
\- Конфликты Gradle: убедитесь, что версии Compose/Voyager/Koin согласованы с \`gradle/libs.versions.toml\`

\---