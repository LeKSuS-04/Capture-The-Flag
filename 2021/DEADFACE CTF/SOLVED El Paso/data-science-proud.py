import pandas as pd

employees = pd.read_csv('employees.csv')
loans = pd.read_csv('loans.csv')

employees_from_ElPaso = list(employees[employees["city"] == "El Paso"]['employee_id'])
loans_from_ElPaso = loans[loans['employee_id'].isin(employees_from_ElPaso)]
answ = loans_from_ElPaso['balance'].sum()

print(answ)