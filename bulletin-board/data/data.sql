create table if not exists roles
(
    id   bigint auto_increment
        primary key,
    name varchar(20) null
);

INSERT INTO extrawest.roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO extrawest.roles (id, name) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO extrawest.roles (id, name) VALUES (3, 'ROLE_USER');

create table if not exists users
(
    id       bigint auto_increment
        primary key,
    username varchar(255) null,
    email    varchar(50)  null,
    phone    varchar(50)  null,
    password varchar(255) null,
    constraint UK6dotkott2kjsp8vw4d0m25fb7
        unique (email),
    constraint UKr43af9ap4edm43mmtq01oddj6
        unique (username)
);

INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (1, 'admin', 'admin@extrawes-test.com', '+1 713.652.5151', '$2a$10$343nY8Vmn1lHMqSaCeLlWeF.1YlUe/EQb4Bjjtdu4XsKzXkOhT2.6');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (2, 'alexandr137', 'alex137@gmail.com', '+1 727.502.8200', '$2a$10$0DTjSww4miJ22TFMOWJvjulF.un0IdbmHyoCD2D5W2dRwc1WkKvIu');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (3, 'sergey12', 'sergey.petrov@yahoo.com', null, '$2a$10$z56YfJRBSIYDb1GMY7YrVOT30ub3U9QM.uDZaG/vCUDxBJn9ecW5C');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (4, 'denis.ivanov', 'deniv.ivanov175@yahoo.com', '+1 803.212.4978', '$2a$10$xpNhM5Se0NGp7HmoShzaye.YRP1k2V4mu08xjC5quc5kTGzDyhtu2');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (5, 'nick', 'nick@gmail.de', null, '$2a$10$XvD/rkzuLgVrPMXf/cwYAeLU5avCQ8.a14K1xOkV5RGt6EfXMB/lm');
INSERT INTO extrawest.users (id, username, email, phone, password) VALUES (6, 'andrey98', 'andrey98@gmail.com', '+1 202.677.4046', '$2a$10$1jtoZWGS3Jxla7DVfS6speMnYEKSwvsURxxTTNzxA6KLQ.gjS0QFC');

create table if not exists user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FKh8ciramu9cc9q3qcqiv4ue8a6
        foreign key (role_id) references roles (id),
    constraint FKhfh9dx7w3ubf1co1vdev94g3f
        foreign key (user_id) references users (id)
);

INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (1, 2);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (3, 2);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (1, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (2, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (3, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (4, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (5, 3);
INSERT INTO extrawest.user_roles (user_id, role_id) VALUES (6, 3);

create table if not exists advertisement
(
    id               bigint auto_increment
        primary key,
    description      longtext     not null,
    photo_url        varchar(255) null,
    price            double       not null,
    publication_date datetime     not null,
    title            varchar(255) not null,
    user_id          bigint       not null
);

create index FKjy2fg8yivvkja3escsflpw8sk
    on advertisement (user_id);

INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (3, 'Защитное стекло 3D на iphone:
Iphone 6/6S/6+/6S+/белое/черное
Iphone 7/8/7+/8+/белое/черное
Iphone Х/XS/XS Max
Iphone 11/11 Pro/11 Pro Max

Дроп от партнёров / быстрая отправка
Новая Почта/Укрпочта
Оплата:
Олх доставка/карта', 'https://ireland.apollo.olxcdn.com/v1/files/d56gh7eyvf2d2-UA/image;s=1000x700', 100, '2020-09-08 15:52:00', 'Защитное стекло 3D iphone айфон 6 8 7+ X Xs Xr 11 11proMax', 2);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (4, 'Описание
Портативная батарея Power Bank Hoco J21 Vintage Wine 10000 mAh - оригинальное дизайнерское решение с формой по типу бутылки.

Особенности Hoco J21: Емкость батареи составляет 10000 mah.

Оснащен функцией «Потряси меня» - встряхните Power Bank и он отобразит остаток энергии на светодиодном индикаторе заряда.

Аккумулятор имеет два USB-выхода на 2.1А с возможностью их одновременного использования.

Данное устройство изготовлено из крепких качественных огнестойких материалов ABS + PC.

Небольшие габаритные размеры позволят вам носить зарядное устройство с собой без какого-либо дискомфорта.

Зарядится телефон может полностью за 1-2 часа.

Светодиодный индикатор повер банка поможет контролировать уровень питания.

Уникальный дизайн не только привлекает внимание, но и весьма практичен.

В устройстве присутствуют интерфейсы подключения: micro-usb и порт usb.

Устройство имеет один кабель в комплекте.

Аксессуар представлен в нескольких оттенках.

Технические характеристики:

Размеры 119 × 69 × 25 мм

Вес 227 г

Цвет желтый

Ёмкость батареи 10000 mAh

Тип батареи Li-Ion

Разъём microUSB

Напряжение 5 V

Входное напряжение 5V / 2.0A

Выход напряжение 5V / 2.1A

Материал корпуса PC-огнестойкий

Комплект: Power Bank Hoco B20A Кабель micro-USB Упаковка

ВАШ ЗАКАЗ:
Мы отправляем новой почтой
БЕЗ ПРЕДОПЛАТЫ
ГАРАНТИЯ 14 ДНЕЙ
ПРИЯТНЫЕ БОНУСЫ КАЖДОМУ', 'https://ireland.apollo.olxcdn.com/v1/files/x7qmx6sui4cb2-UA/image;s=1000x700', 550, '2020-08-31 08:20:00', 'Power Bank Hoco 10000 mAh', 2);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (5, 'Телефон официальный, ещё на гарантии.
Был подарен в марте месяце, телефоном не пользовался так как постоянно работаю на высоте (боялся разбить).

Сразу в магазине поклеена гелевая пленка, царапины, сколы, потёртости - отсутствуют.
Комплект полный. Торг небольшой.', 'https://ireland.apollo.olxcdn.com/v1/files/rfpfjc3xffg13-UA/image;s=1000x700', 9000, '2020-09-04 13:31:00', 'Xiaomi Mi 9 t pro 6/128', 3);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (6, 'Магнитный мега удобный кабель SuperCalla который никогда не запутается- является незаменимой вещью в современном мире гаджетов.

Кабель предназначен для истинных ценителей порядка в своих вещах!

Этот кабель очень удобно носить в сумке, потому что он всегда аккуратно сложен.
Кабель оснащен магнитными кольцами, расположенными на одинаковом расстоянии друг от друга по всей длине кабеля.
Они помогают кабелю автоматически складываться, упрощая пользование и подзарядку девайсов.
Подходит для смартфонов, планшетов, и Аpple watch.
Кабель выполнен из силикона, с кольцами-магнитами. Длина: 0.9 м.1.8 м
Удобен в использовании, прекрасно подойдет для организации рабочего места, командировок, путешествий.
Он компактно складывается, занимает мало места, не путается и не заламывается.

Количество ограниченно

Для заказа пишем в сообщения или на вайбер!', 'https://ireland.apollo.olxcdn.com/v1/files/eqla4um521c01-UA/image;s=1000x700', 239, '2020-08-26 01:43:00', 'Магнитный кабель SuperCalla, для iPhone Apple Type-C Micro USB', 3);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (7, 'ОЧЕНЬ СРОЧНО!
До пятницы за 4999грн!
айфон 7,32 гб
телефон в хорошем состоянии,есть несколько царапин и повреждений(на фото они видны).
Аккумулятор: 100%
информацией,обращайтесь,в лс или по телефону
в комплекте-ТОЛЬКО зарядка', 'https://ireland.apollo.olxcdn.com/v1/files/xbx65gkh6uhv1-UA/image;s=1000x700', 4999, '2020-08-03 23:47:00', 'IPhone 7 СРОЧНО!', 3);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (8, 'Очки виртуальной реальности, изображение выводится на экран смартфона, подходит для смартфонов диагональю 5.1 – 5.7", устройство совместимо с Android, угол обзора 96°, регулировка фокусного расстояния.', 'https://ireland.apollo.olxcdn.com/v1/files/ei5d0ndnyxkd-UA/image;s=1000x700', 800, '2020-07-29 14:05:00', 'Очки виртаульной реальности Samsung Gear VR (SM-R322)', 4);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (9, 'Телефон работает, включается
iCloud не заблокирован, заблокирован на операторе
По всем вопросам звоните пишите', 'https://ireland.apollo.olxcdn.com/v1/files/tu9rraw2ganm2-UA/image;s=1000x700', 500, '2020-09-02 10:10:00', 'Iphone 6s на запчасти', 5);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (10, 'Телефон почти без царапин, появилась полоска после не большого падение, телефон носился в чехле и защитным стеклом, покупался на новый год 2020.
По функционалу проблем нет, единственный минус эта полоска на экране, потому цена и занижена, по желанию все меняется.', 'https://ireland.apollo.olxcdn.com/v1/files/7efw6guom4w1-UA/image;s=1000x700', 9500, '2020-08-17 15:29:00', 'Iphone X 64gb', 5);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (11, 'Apple Iphone Xr 64gb

Оригинал

Новый, запечатанный

Не восстановленный

Neverlock

Цвета:

-Yellow
-White
-Blue
-Red

Количество SIM-карт - 1

Диагональ экрана - 6.1

Емкость аккумулятора - 2942 MAh

Основная камера - 12 МП

Фронтальная камера - 7 Мп

Оперативная память - 3 ГБ

Внутренняя память - 64 ГБ

Процесор - Apple A12 Bionic', 'https://ireland.apollo.olxcdn.com/v1/files/0itob9v9c6p3-UA/image;s=1000x700', 16200, '2020-09-07 18:55:00', 'Apple Iphone Xr 64gb Новые Neverlock, Оригинал, Не восстановленные', 6);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (12, 'Xiaomi Mi Band 4
Фитнес браслет М4

Качественная кoпия
Заводское Качество!

Отправка Без предоплаты.

Браслет Mi Band 4 – это новое поколение фитнес-трекера Mi Band 4 ЭТО СУПЕР НОВИНКА ПО СУПЕР ЦЕНЕ!
Материалы конструкции: корпус разборный, ремешок - силикон
Вес: 40 г
Экран: цветной, дисплей 0,90 дюйма
Сенсорная кнопка
Связь: от Bluetooth 4.2
Индикация: экран+вибросигнал
Датчики: акселерометр, датчик сердечного ритма, GPS, вибро
Совместимость: iOS 7.0+ / Android 4.4+, iOS9+
Аккумулятор: 170 мАч

Влагозащиты у браслета нету, мочить их нельзя!

Отправка Без Предоплаты
- Новой Почтой каждого дня у 16:00
- Укрпочтой среда и пятница

Для заказа,пожалуйста, напишите:
- полное Ф.И.О. ;
- контактный телефон;
- город;
- № отделения Новой Почты в Вашем городе;
- способ оплаты, который Вам подходит.', 'https://ireland.apollo.olxcdn.com/v1/files/p12qaj8k3kyi-UA/image;s=1000x700', 195, '2020-09-06 21:37:00', '-30% Фитнес Браслет Трекер Xiaomi Mi Band M4 часы Ми бенд 4', 6);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (13, 'bit.ly/citrus-huawei – переходи по ссылке

Более 2 тыс. товаров в каталоге
Опт и Розничные продажи. Скидки на объёме!

В продаже оригинальные Смартфоны Huawei с гарантией различного состояния:
• Идеальное (A+)
• Близко к идеалу (A)
• Хорошее, есть следы эксплуатации (B)
• Заметные следы эксплуатации (С)

Все устройства тщательно протестированы и готовы к использованию.
Всегда есть возможность проверить устройство перед покупкой и удостовериться в его качестве. И даже после покупки, если товар не подошёл, вернуть или обменять в течение 14-ти дней. Ну круто же, правда?

Дополнительно:
• Оплата Частями (Моно /Приват) и Рассрочка
• Trade-In! Возможность обменять старый телефон с доплатой;
• Помощь в подборе устройства;
• Гарантия и документы о покупке;
• Не привязаны к учетной записи Apple ID, Google и др. (проверено).

Доставка службой «Нова Пошта» с наложенным платежом, а также самовывоз из сервис-центров по адресам:

Киев, ул. Большая Васильковская, 8
Киев, ул. Институтская, 2
Одесса ул. Ак. Заболотного, 52
Одесса ул. Маразлиевская, 1/20
Одесса ул. Пантелеймоновская, 21
Днепр, пр. Дмитрия Яворницкого, 55
Харьков, ул. Пушкинская, 36
Николаев, ул. Потемкинская, 52/3
Черноморск, пр. Мира, 10', 'https://ireland.apollo.olxcdn.com/v1/files/29an5513jl8j2-UA/image;s=1000x700', 2999, '2020-06-20 11:52:00', 'Б/у Смартфоны Huawei P30/20/10/9/8 Lite/Pro, Y, Mate, Nova – Цитрус', 6);
INSERT INTO extrawest.advertisement (id, description, photo_url, price, publication_date, title, user_id) VALUES (14, 'new test advertisement', 'https://www.google.com', 100, '2020-09-10 21:01:06', 'new advertisement', 2);