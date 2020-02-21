 **GrowIt Back-End Project.**
 
DB:
=====
Set all "id" fields in postgres as "Auto-increment" checkboxes chosen.
You can change start position for generation simply changing its sequense(all sql present in default_data.sql).



REST API endpoints
========

One user can be both borrower and investor(profile per table):
1) With one email(data is copied from another profile)
2) With different email: apart different profile
3) Password can be common for both profiles

=BORROWER=
=post- login
=post- registration
// asks for a wished loan
post- phones, fax + SMS phone verification

post- name, Passport(...)
post- ITN
post- CreditCard
post- Address(1 за пропискою, 2 фактична адреса, можна клікнути, що співпадає з адр. реєстрації)
post- HomeOwnership
post- family(married, kids (before and after 18 y.o) )
post- education(with level)
post- employer info +
post- put- set ContactPersons
[LOAN]post- comment(loan purpose, plan on return)

put- update married, kids, address, Employment, jobTitle, homeOwnership, incomes, work sphere, phone, Instagram, FB
put- change password
put- change email

=BORROWER ACCOUNT=
[credit card number of investor]
[contract]
post- withdraw account
post - repay + photo/screenshot (+ payment system in full version)
get- current loans info(scheduled payment days, amount left)
get- previous loans
get- account transactions list


possessions(type of housing, car, business)

=CREDIT CARD=
post- add card(up to 5)
delete-  card
put- update card


=INVESTOR=
post- login
post- registration
post- name, Passport(...)
post- ITN
post- investment    -> put- update loan 

=INVESTOR ACCOUNT=
post- deposit account
get- investments
get-  statistics
put- change reg_address
put- change password
put- change email

=INVESTMENT=
post- make an investment

=LEGAL=
get- gorm an agreement(B and I manes, passport, itn, reg_address, data on input)
post- sign *_agreement



=LOAN=
right after Borrower registers: 
post- createLoan(LoanWishedDto: ) wished amount and period of loan(on front-end dynamically reflects return date, dates of payments if more than month)
get- loanList (DashboardLoan)
get- loanInfo -> -get borrowerVerification
get- investorAccountSummary -> -get investments, calculated parameters
get- borrower cabinet data -> get borrower's previous loans
get- legal contract
put- update loan
delete- for admin






post- 

post- verificationData
post- sign legal contract



Admin:

get- list of all borrowers with appropriate sorts & searches
get- list of all investors with appropriate sorts & searches
get- list of all investments with appropriate sorts & searches
get- list of all transactions with appropriate sorts & searches
get- list of all loans with appropriate sorts & searches

put- update all involved entities 

delete- update all involved entities 


=Employment=

put- add new employment(s)
put- update Employment


=EXTERNAL=
+post send photo with passport for verification
+post login creds UBKI
+post borrower info needed for report -> create CreditHistory from response



+email verification
+static storage config for uploaded photo with passport, ipn

+write tests with UBKI test url sending POST requests
