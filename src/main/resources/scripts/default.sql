
insert into loan_status (id, created, updated, status_eng, status_ua) values (70, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'On funding', 'Фінансується');
insert into loan_status (id, created, updated, status_eng, status_ua) values (71, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Issued', 'Займ виданий');
insert into loan_status (id, created, updated, status_eng, status_ua) values (72, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Fully returned', 'Повністю повернутий');
insert into loan_status (id, created, updated, status_eng, status_ua) values (73, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Partially returned', 'Частково повернутий');
insert into loan_status (id, created, updated, status_eng, status_ua) values (74, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Sold to another investor', 'Проданий іншому інвестору');
insert into loan_status (id, created, updated, status_eng, status_ua) values (75, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Sold to collector', 'Проданий колектору');
insert into loan_status (id, created, updated, status_eng, status_ua) values (76, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'On grace period', 'На безштрафному періоді');
insert into loan_status (id, created, updated, status_eng, status_ua) values (77, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', '6 to 14 days late', '6-14 днів протермінування');
insert into loan_status (id, created, updated, status_eng, status_ua) values (78, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', '15 to 31 days late', '15-31 днів протермінування');
insert into loan_status (id, created, updated, status_eng, status_ua) values (79, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', '32 to 120 days late', '32-120 днів протермінування');
insert into loan_status (id, created, updated, status_eng, status_ua) values (80, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Charged off', 'Списано як безнадійна заборгованість');
insert into loan_status (id, created, updated, status_eng, status_ua) values (81, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Created', 'Заявка створена');


insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (325, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Car service', 'Сервіс авто');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (326, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Home improvements', 'Ремонт помешкання');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (327, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Medical bills', 'Оплата лікування');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (328, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Repaying debt', 'Погашення іншого кредиту');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (329, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Vacation/trip', 'Відпустка/поїздка');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (330, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Business expenses', 'Потреби бізнесу');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (331, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Electronics purchases', 'Купівля електроніки');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (332, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Special Event', 'Святкування події');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (333, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Shopping', 'Шопінг');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (334, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Education expenses', 'Оплата навчання');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (335, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Buying a gift', 'Купівля подарунка');
insert into loan_purpose (id, created, updated, purpose_eng, purpose_ua) values (336, '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554', 'Fees/fines', 'Оплата рахунків/штрафів');

'Сервіс авто',
'Ремонт помешкання',
'Погашення іншого кредиту',
'Оплата лікування',
'Відпустка/поїздка',
'Потреби бізнесу',
'Купівля електроніки',
'Святкування події',
'Шопінг',
'Оплата навчання',
'Купівля подарунка',
'Оплата рахунків/штрафів'


insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(1, 'Car servicing / Transport / Logistics', 'Автосервіс / Транспорт / Логістика', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(153, 'Audit / Legal / Consulting', 'Аудит / Юр. сфера / Консалтинг', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(154, 'Building / Architecture', 'Будівництво / Архітектура', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(155, 'Heavy industry', 'Важка промисловість', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(156, 'Hotels / Restaurants / Gambling', 'Готелі / Ресторани / Казино', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(157, 'Wholesale / Storages', 'Оптова торгівля / Склади', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');


insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(359, 'Local / state Governance', 'Державне / Місцеве управління', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(361, 'Energetics / Oil / Gas', 'Енергетика / Нафта / Газ', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(362, 'Armed forces', 'Збройні сили', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(363, 'IT / Telecommunication', 'IT / Телекомунікації', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(364, 'Light industry', 'Легка промисловість', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(365, 'State medicine / pharmacology', 'Медицина / Фармакологія (державна)', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(366, 'Commercial medicine / pharmacology', 'Медицина / Фармакологія (комерційна)', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(377, 'Media / Publishing / Printing', 'Медіа / Видавництво / Поліграфія', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(378, 'Police / Security services', 'Поліція / Безпека / Правоохоронні органи', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(379, 'Science', 'Наука', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(380, 'Real estate', 'Нерухомість', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(381, 'Education / Culture', 'Освіта / Культура', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(382, 'Advertisement / Marketing / Sales', 'Реклама / Маркетинг / Продажі', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(383, 'Entertainment / Show business', 'Розваги / Шоу-бізнес', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(384, 'Retail', 'Роздрібна торгівля', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(385, 'Agriculture', 'Сільське господарство', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(386, 'Tourism / Sport / Health care', 'Туризм / Спорт / Догляд за здоров''ям', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(387, 'Finance / Bank / Insurance', 'Фінанси / Банківська справа / Страхування', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, updated) values
(388, 'Chemical Industry', 'Хімічна промисловість', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');


insert into social_status (id, status_ua, status_eng, created, updated) values (389, 'Повна зайнятість', 'Full-time employment', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (390, 'Часткова зайнятість', 'Part-time employment', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (391, 'Підприємець', 'Entrepreneur', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (392, 'Студент', 'Student', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (393, 'Пенсіонер', 'Retired', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (394, 'Зайнятий пенсіонер', 'Working retired', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (395, 'Тимчасово не працюю', 'Temporarily unemployed', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (396, 'Робота за кордоном/сезонна', 'Working abroad / seasonal', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (397, 'Самозайнята особа', 'Self-employed', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into social_status (id, status_ua, status_eng, created, updated) values (398, 'Декрента відпустка', 'Maternity leave', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');

