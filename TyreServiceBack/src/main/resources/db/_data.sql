INSERT INTO PARTNERS(id, name, description, email, password, date_work, phone, rank, address, latitude, longitude, type) VALUES
    (1, 'Рога и копыта', 'Занимаемся подрезкой копыт и шлифовкой рогов',
    'rog@gmail.com','123456','08:00 - 22:00', '8-910-234-56-78', 3, 'Адрес 1', 10.343, 12.435, 'CAR'),
    (2, 'Хвост и усы', 'Занимаемся закручиванием усов и стрижкой хвостов',
        'hvost@gmail.com','123456','08:00 - 22:00', '8-911-234-56-78', 2, 'Адрес 2', 12.343, 15.435, 'CAR')
;

INSERT INTO WORKS(id, name, description, type_wheel) VALUES
    (1, 'Монтаж', 'Монтаж шины на диск, накачивание колеса до необходимого давления', 'R13'),
    (2, 'Демонтаж', 'Снятие шины с диска колеса', 'R13'),
    (3, 'Монтаж', 'Монтаж шины на диск, накачивание колеса до необходимого давления', 'R14'),
    (4, 'Демонтаж', 'Снятие шины с диска колеса', 'R14')
;

INSERT INTO COST_WORKS(id, partner_id, work_id, cost) VALUES
    (1, 1, 1, 150),
    (2, 1, 2, 50),
    (3, 1, 3, 160),
    (4, 1, 4, 50),
    (5, 2, 1, 140),
    (6, 2, 2, 50)
;