INSERT INTO PARTNER(id, name, description, email, password, date_work, phone_number, rank, address, latitude, longitude) VALUES
    (1, 'Рога и копыта', 'Занимаемся подрезкой копыт и шлифовкой рогов',
    'rog@gmail.com','123456','08:00 - 22:00', '8-910-234-56-78', 3, 'Адрес 1', 10.343, 12.435),
    (2, 'Хвост и усы', 'Занимаемся закручиванием усов и стрижкой хвостов',
        'hvost@gmail.com','123456','08:00 - 22:00', '8-911-234-56-78', 2, 'Адрес 2', 12.343, 15.435)
;

INSERT INTO SERVICE(id, name, description, price, service_id, car_type, state_wheel_type) VALUES
    (1, 'Монтаж', 'Монтаж шины на диск, накачивание колеса до необходимого давления', 100, 1, 'PASSENGER', 'R13'),
    (2, 'Монтаж', 'Монтаж шины на диск, накачивание колеса до необходимого давления', 120, 1, 'PASSENGER', 'R14')
;