package com.growit.api.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@Table(name = "work_sphere")
@EqualsAndHashCode(callSuper = false)
public class WorkSphere extends AbstractEntity {

    private String sphereEng;

    private String sphereUa;

}

/**  List of basic work spheres:
 Car servicing / Transport / Logistics
 Audit / Legal / Consulting
 Building / Architecture
 Heavy industry
 Hotels / Restaurants / Gambling
 Wholesale / Storages
 Local / state Governance
 Energetics / Oil / Gas
 Armed forces
 IT / Telecommunication
 Light industry
 State medicine / pharmacology
 Commercial medicine / pharmacology
 Media / Publishing / Printing
 Police / Security services
 Science
 Real estate
 Education / Culture
 Advertisement / Marketing / Sales
 Entertainment / Show business
 Retail
 Agriculture
 Tourism / Sport / Health care
 Finance / Bank / Insurance
 Chemical Industry

 ====================================

'Автосервіс / Транспорт / Логістика'
'Аудит / Юр. сфера / Консалтинг'
'Будівництво / Архітектура'
'Важка промисловість'
 'Готелі / Ресторани / Казино'
'Оптова торгівля / Склади'

 'Державне / Місцеве управління'
'Енергетика / Нафта / Газ'
 'Збройні сили'
 'IT / Телекомунікації'
 'Легка промисловість'
 'Медицина / Фармакологія (державна)'
 'Медицина / Фармакологія (комерційна)'
 'Медіа / Видавництво / Поліграфія'
 'Поліція / Безпека / Правоохоронні органи'
 'Наука',
 'Нерухомість'
 'Освіта / Культура'
 'Реклама / Маркетинг / Продажі'
 'Розваги / Шоу-бізнес'
 'Роздрібна торгівля'
 'Сільське господарство'
 'Туризм / Спорт / Догляд за здоров'ям'
 'Фінанси / Банківська справа / Страхування'
 'Хімічна промисловість'
 ====================================

 Сельское хозяйство, лесное хозяйство и рыбное хозяйствоСільське господарство, лісове господарство та рибне господарство

 Добывающая промышленность и разработка карьеров.

 Перерабатывающая промышленность

 Поставка электроэнергии, газа и кондиционированного воздуха

 Водоснабжение канализация, обращение с отходами

 Строительство

 Оптовая и розничная торговля ремонт автотранспортных средств и мотоциклов

 Транспорт, складское хозяйство, почтовая и курь'ерська деятельность

 Временное размещения и организация питания

 Информация и телекоммуникации

 Финансовая и страховая деятельность

 Операции с недвижимым имуществом

 Профессиональная, научная и техническая деятельность

 Деятельность в сфере административного и вспомогательного обслуживания

 Деятельность в сфере административного и вспомогательного обслуживания

 Образование

 Охрана здоровя и предоставление социальной помощи

 Искусство, спорт, развлечения и отдых

 Предоставление других видов услуг

 Деятельность домашних хозяйств

 Деятельность экстерриториальных организаций и органов
 */
