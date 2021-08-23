USE hotel;
insert into rooms(number, capacity, status, comfort, price) values (1, 3, 0, 5, 30.00), (2, 2, 0, 4, 20.00),
					(3, 6, 0, 3, 15.00), (4, 1, 0, 5, 40.00), (5, 4, 0, 4, 25.00);
insert into guests(name, days_of_stay, room_id) values ('Pavel', 5,  1), ('Naty', 4, 2),
 ('Ilya', 3, 2), ('Elena', 1, 2), ('Alex', 1, 4);     
 insert into services(name, price, guest_id) values ('gym', 20.00, 5), ('salon', 10.00, 4),  
('bar', 70.00, 3), ('dry clean', 15.00, 2), ('hall', 45.00, 1);    