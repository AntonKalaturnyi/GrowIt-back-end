 **GrowIt Back-End Project.**
 
DB:
=====
Set all "id" fields in postgres as "Auto-increment" checkboxes chosen.
You can change start position for generation simply changing its sequense(all sql present in default_data.sql).



Quiries
========

get- loanList (DashboardLoan)
get- loanInfo -> -get borrowerVerification
get- transactionsList
get- investorAccountSummary -> -get investments, calculated parameters
get- borrower cabinet data -> get borrower's previous loans
get- legal contract

post- login/registration for borrower/investor
post- createLoan
post- investment    -> put- update loan 
post- verificationData
post- sign legal contract

put- update loan

Admin:

get- list of all borrowers with appropriate sorts & searches
get- list of all investors with appropriate sorts & searches
get- list of all investments with appropriate sorts & searches
get- list of all transactions with appropriate sorts & searches
get- list of all loans with appropriate sorts & searches

put- update all involved entities 

delete- update all involved entities 
