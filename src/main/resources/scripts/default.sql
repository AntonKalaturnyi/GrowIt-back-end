
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
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(7, 'Local Governance', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(8, 'State Governance', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(9, 'Energetics / Oil / Gas', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(10, 'Armed forces', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(11, 'IT / Telecommunication', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(12, 'Light industry', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(13, 'State medicine / pharmacology', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(14, 'Commercial medicine / pharmacology', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(15, 'Media / Publishing / Printing', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(16, 'Police / Security services', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(17, 'Science', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(18, 'Real estate', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(19, 'Education / Culture', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(20, 'Advertisement / Marketing / Promotion', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(21, 'Entertainment / Show business', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(22, 'Retail', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(23, 'Agriculture', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(24, 'Tourism / Sport / Health care', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(25, 'Finance / Bank / Insurance', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
insert into work_sphere(id, sphere_eng, sphere_ua, created, created, updated) values
(26, 'Chemical Industry', '2020-01-31 18:05:19.554', '2020-01-31 18:05:19.554');
