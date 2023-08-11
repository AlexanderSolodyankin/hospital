АС «Больница»

Цель: Система управления деятельностью больницы.
Пользователи:
1.	Администратор системы.
2.	Главный врач.
3.	Заведующий отделением.
4.	Врач.
5.	Заведующий лабораторией.
6.	Лаборант.
7.	Интерн.
8.	Мед. брат/ Мед. сестра.
9.	Регистратура.
10.	Пациент.

Доступные действия по ролям:

Администратор системы:
1.	Просмотр и редактирование ролей пользователей.
    1) Получить всех пользователей (всех пользователей, по роли, по имени, по дате рождения ..... тд)
    2) Передать пользователя и измененную роль, (Роль можно удолить, добавить)
    3) получить пользователя по (логин, id, почта)
    ======================================= Для этого нужны сущности:            ===========================
    ============ User(id, login, password, email, date_create, status, @ManyToMany_Role_id),             ===           
    ============ Role(id, role_title, @ManyToMany_user_id),                                              ===            
    ============ UserInfo(id, name, ser_name, patrole, data_berth, phone, address @OneToOne_user_id)     ===

Главный врач:
1.	Просмотр списка отделений.
    1) Получение списка отделений
2.	Просмотр списка лабораторий.
    1) получение списка лобароторий
3.	Добавление, редактирование и увольнение работников.
    1) получение списка сотрудников
    2) добавление нового сотрудников
    3) редактирование сотрудника
    4) увольнение (Удоление) сотрдника из системы
4.	Формирование отчетов.
    1) получение всех отчетов из всех отделений и формирования полного отчета по больнице
    2) формирование собственного отчета с коментарием от глав врача на основе полученного отчета
5.	Издание указов для больницы.
    1) Получение всех указов
    2) создание указа
    3) изменение указа
    4) удоление указа
    ======================================  Для этого нужны сущности:           ==============================
    =========== head_doctor(id, date_create, date_over, @o2n_doctor_id)  ============================
    =========== departament(id, depar_name, departament_discript, @m2m_doctor_id, заведующий_id) =============
    =========== lobarotory(id, lab_name, lab_discript, @m2m_лаборант_id, заведующий_id)          =============
    =========== employ(id,employ_type, @o2o_user_id, date_beginn, salary, departamrt_id )




Заведующий отделением:
1.	Просмотр врачей по своему отделению.
2.	Просмотр пациентов поступивших в отделение.
3.	Назначение врача для лечение пациента.
4.	Приставление и отвязка интерна от врача.
5.	Формирование списка дежурств по отделению.
6.	Формирование отчетов.
7.	Повышение интерна до врача.



Врач:
1.	Получение списка своих пациентов.
2.	Осмотр пациента.
3.	Завершение лечения пациента.
4.	Получение истории лечения своих пациентов.

Заведующий лабораторией:
1.	Просмотр лаборантов по своей лаборатории.
2.	Просмотр анализов поступивших в лабораторию.
3.	Назначение лаборанта на проведение анализа.
4.	Формирование отчетов.

Лаборант:
1.	Получение списка поступивших анализов.
2.	Проведение анализа.

Интерн:
1.	Просмотр информации о текущем враче — наставнике.
2.	Получение списка заданий от врача — наставника.
3.	Выполнение задания от врача — наставника.

Мед. брат/ Мед. сестра:
1.	Просмотр пациентов нуждающихся в уходе.
2.	Уход за пациентом.
3.	Взятие анализов у пациентов.

Регистратура:
1.	Прием пациента на лечение.
2.	Выписка пациента.

Пациент:
1.	Просмотр истории своих болезней.
2.	Просмотр рецепта врача по конкретной болезни.
3.	Составление отзыва о конкретном враче.





Некоторые действия, требующие конкретики:


Прием пациента на лечение:
1.	Регистратура заполняет карточку Пациента и регистрирует его в больнице.
2.	Регистратура принимает жалобы Пациента и направляет его Заведующему отделения, соответствующему жалобам Пациента.
3.	Заведующий отделения назначает Врача на лечение Пациента и первое доступное место в палате.
4.	Врач проводит осмотр пациента.
5.	Если Врач назначил сдачу анализов, он назначает Мед. брата/Мед. сестру на взятие анализов. Мед. брат/Мед. сестра производят взятие анализов и передают их Заведующему лабораторией. Заведующий лабораторией назначает Лаборанта на проведение анализа. Лаборант проводит анализ, составляет решение и передает Врачу. 
6.	Если Врач назначил лекарства/уколы, он формирует график и отправляет Мед.брату/Мед.сестре. Интерн заносит эти данные в рецепт по истории болезни Пациента.
7.	Если Врач провел осмотр и Пациент здоров, он переводит Пациента на Регистратуру для выписки. 
8.	Пациент(по желанию) заполняет отзыв о Враче.
9.	Интерн ведет у себя историю действий Врача, указывая какой Врач, какая болезнь и действия совершенные Врачом.
