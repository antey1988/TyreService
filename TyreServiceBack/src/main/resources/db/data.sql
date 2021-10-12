INSERT INTO PARTNERS(id, name, description, address, email, phone, password, schedule, rank, latitude, longitude, type) VALUES
    (1, 'Рога и копыта', 'Занимаемся подрезкой копыт и шлифовкой рогов', 'Адрес 1',
    'rog@gmail.com', '8-910-234-56-78','123456','08:00 - 22:00', 3.9, 10.0, 12.0, 'PASSENGER'),
    (2, 'Хвост и усы', 'Занимаемся закручиванием усов и стрижкой хвостов', 'Адрес 2',
        'hvost@gmail.com', '8-911-234-56-78','123456','08:00 - 22:00', 2.8, 12.0, 17.0, 'PASSENGER'),
    (3, 'Копыта и рога', 'Занимаемся подрезкой копыт и шлифовкой рогов', 'Адрес 3',
        'kopita@gmail.com', '8-910-234-56-78','123456','08:00 - 22:00', 3.5, 10.0, 12.0, 'OFFROAD'),
    (4, 'Усы и хвост', 'Занимаемся закручиванием усов и стрижкой хвостов', 'Адрес 4',
        'usi@gmail.com', '8-911-234-56-78','123456','08:00 - 22:00', 3.0, 11.0, 15.0, 'OFFROAD')
;

INSERT INTO WORKS(id, name, description, type) VALUES
    (1, 'Монтаж', '', 'R13'),
    (2, 'Демонтаж', '', 'R13'),
    (3, 'Монтаж', '', 'R14'),
    (4, 'Демонтаж', '', 'R14')
;

INSERT INTO COSTS_WORKS(partner_id, work_id, price) VALUES
    (1, 1, 100),
    (1, 2, 50),
    (1, 3, 110),
    (1, 4, 50),
    (2, 1, 95),
    (2, 2, 50),
    (3, 3, 105),
    (3, 4, 45)
;

INSERT INTO ORDERS(id, partner_id, create_date, booking_date, status, client, phone, auto) VALUES
    (1, 1, '2020-10-01', '2020-10-02','WAITING', 'Ivan', '892815748596145', 'fgfggg')
;

INSERT INTO LINES_ORDER(order_id, work_id, price, count) VALUES
    (1, 1, 100, 4),
    (1, 2, 50, 4)
;