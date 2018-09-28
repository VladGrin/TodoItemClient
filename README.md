# TodoItemClient
Инструкция для запуска приложения на Windows.
- для запуска приложения требуется Java SE 8.
- запустите файл todoitemclient.bat
- для доступа используйте порт 9990

Инструкция по созданию приложения.

Для сборки использовался Apache Maven.
Для разработки проекта использовался Spring Framework (Spring Boot, Spring MVC).
Тело кода разбито на такие слои – пакеты: entity, controller, service, repository, formatter, exception.

Для получения информации с сервера todoitemserver в пакете service
я создал интерфейс MainServerReader и его реализацию MainServerReaderImpl. 
Его цель связаться с todoitemserver и получить response класса Response.
Обработкой полученного response занимается интерфейс BackupHandler с реализацией 
классом BackupHanddlerImpl, которые находятся в пакете service. 
Методы этого класса позволяют получить из response список user, 
список todo и обьект класса Backup и записывает эти данные в базу данных.

В пакете entity находятся POJO классы с необходимыми сущностями:
- Backup имеет id, date, status, список users(связь one to many);
- User имеет id, userId(id полученого user), username, email, backup(связь many to one), список todos(связь one to many);
- Todo имеет id, todoId(id полученого todo), subject, dueDate, done, user(связь many to one);

Данные сохраняются в базе данных todo_item_client (MySQL) на сервере https://jelastic.com/
В пакете repository находятся методы для работы с базой данной.(Использовал Spring JPA.

Пакет service содержит классы которые получают данные из репозитория и передаёт в контроллеры. 
В пакете service так же находится пакет model c POJO классами BackupAccounts и ListBackups, 
обьекты которых будут возвращать контроллеры. Классы пакета service обрабатывают данные 
полученные с репозитория и после обработки передают в контроллеры в нужном виде.
Для получения данных в формате CSV используется класс formatter, который содержит методы,
принимающие обьект класса Backup и возвращает данные в нужном формате.

Пакет controller содержит класс BackupController и контроллеры для доступа к сохранённым данным.
- Метод addBackup запускает механизм получения данных с TodoItemServer и сохранение их в базе данных. 
  Так же возвращает обьекта класса BackupAccounts.
-  Метод listBackups возвращает обьект класса ListBackups из базы данных.
-  Метод exportBackup получает необходимые по заданию данные в формате CSV из базы данных.
