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

BORROWER
post- login
post- registration

education
family(married, kids)


INVESTOR



LOAN
right after Borrower registers: 
-post wished amount and period of loan(on front-end dynamically reflects return date, dates of payments if more than month)




get- loanList (DashboardLoan)
get- loanInfo -> -get borrowerVerification
get- transactionsList
get- investorAccountSummary -> -get investments, calculated parameters
get- borrower cabinet data -> get borrower's previous loans
get- legal contract



post- createLoan(LoanWishedDto: )

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
