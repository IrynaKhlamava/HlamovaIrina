USE pc_market;

#1 Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и hd
select model, speed, hd from pc where price < 500;

#2 Найдите производителей принтеров. Вывести: maker
select distinct maker from product where type = 'Printer';

#3 Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол.
select model, ram, screen from laptop where price > 1000;

#4 Найдите все записи таблицы Printer для цветных принтеров.
select * from printer where color = 'y';

#5 Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.
select model, speed, hd from pc where cd in ( '12', '24') and price < 600;

#6 Укажите производителя и скорость для тех ПК-блокнотов, которые имеют жесткий диск объемом не менее 10 Гбайт.
select distinct maker, speed from product inner join laptop on laptop.model = product.model where laptop.hd > 10;

#7 Найдите номера моделей и цены всех продуктов (любого типа), выпущенных производителем B (латинская буква).
select laptop.model, price from product inner join laptop on product.model = laptop.model where maker = 'DELL'
union select pc.model, price from product inner join pc on product.model = pc.model where maker = 'DELL'
union select printer.model, price from product inner join printer on product.model = printer.model where maker = 'DELL';

#8 Найдите производителя, выпускающего ПК, но не ПК-блокноты.
select maker from product where	type = 'PC' and maker not in (select maker from product where type = 'laptop');
            
#9 Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker
select distinct maker from product join pc on product.model = pc.model where speed > 450;

#10 Найдите принтеры, имеющие самую высокую цену. Вывести: model, price
select model, price from printer where price = (select max(price) from printer);

#11 Найдите среднюю скорость ПК.
select avg(speed) from pc;

#12 Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.
select avg(speed) from laptop where price > 1000;

#13 Найдите среднюю скорость ПК, выпущенных производителем A.
select avg(speed) from pc join product on product.model = pc.model where maker = 'ASUS';

#14 Для каждого значения скорости найдите среднюю стоимость ПК с такой же скоростью процессора. Вывести: скорость, средняя цена
select speed, avg(price) from pc group by speed;
 
#15 Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD
select hd from pc group by hd having hd >= 2;

#16 Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара указывается только один раз,
#т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM
select distinct pc1.model, pc2.model, pc1.speed, pc1.ram from pc pc1, pc pc2
where pc1.speed = pc2.speed and pc1.ram = pc2.ram and pc1.model > pc2.model;

#17 Найдите модели ПК-блокнотов, скорость которых меньше скорости любого из ПК. Вывести: type, model, speed
select distinct p.type, p.model, lp.speed from laptop lp
join product p on p.model = lp.model
where lp.speed < (select min(speed) from pc);

#18 Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price
select prd.maker, prn.price from printer prn, product prd
where prd.model = prn.model and prn.color = 'y' and prn.price = (
select min(price) from printer where prn.color = 'y');

#19 Для каждого производителя найдите средний размер экрана выпускаемых им ПК-блокнотов. Вывести: maker, средний размер экрана.
select prd.maker, avg(screen) from laptop lp
left join product prd on prd.model = lp.model
group by prd.maker;

#20 Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести: Maker, число моделей
select maker, count(model) from product where type = 'laptop'
group by maker
having count(model) >=2 ;

#21 Найдите максимальную цену ПК, выпускаемых каждым производителем. Вывести: maker, максимальная цена.
select maker, max(price) from pc, product
where product.model = pc.model
group by maker;

#22 Для каждого значения скорости ПК, превышающего 600 МГц, определите среднюю цену ПК с такой же скоростью. Вывести: speed, средняя цена.
select speed, avg(price)
from pc
where speed > 200
group by speed;

#23 Найдите производителей, которые производили бы как ПК со скоростью не менее 750 МГц, так и ПК-блокноты со скоростью не менее 750 МГц. Вывести: Maker
select maker from product prd
join pc on prd.model = pc.model
where pc.speed > 100 AND prd.maker IN ( select maker from product
join laptop on product.model = laptop.model where laptop.speed > 500);

#24 Перечислите номера моделей любых типов, имеющих самую высокую цену по всей имеющейся в базе данных продукции.
select model from
(select model, price from pc
union select model, price from laptop
union select model, price from printer) frs
where price = (select max(price) from
(select price from pc
union select price from laptop
union select price from printer) scnd );

#25 Найдите производителей принтеров, которые производят ПК с наименьшим объемом RAM и с самым быстрым процессором среди всех ПК, имеющих наименьший объем RAM. Вывести: Maker
select maker from product where model in (
select model from pc where ram = (
select min(ram) from pc) and speed = (
select max(speed) from pc where ram = (select min(ram) from pc)))
and maker in (select maker from product where type = 'printer');
