import pandas as pd
from pandas.core.frame import DataFrame

'''
De Monne has reason to believe that DEADFACE will 
target loans issued by employees in California. 
It only makes sense that they'll then target the city 
with the highest dollar value of loans issued. 
Which city in California has the most money in 
outstanding Small Business loans? 

Submit the city and dollar value as the flag 
in this format: flag{City_$#,###.##}
'''

employees = pd.read_csv('employees.csv')
loans = pd.read_csv('loans.csv')

employees_from_cali = employees[employees["state"] == 'CA']
cali_cities = sorted(list(set(employees_from_cali['city'])))
city_value = pd.DataFrame(columns=['city', 'dollar_value'])

for city in cali_cities:
    employees_from_city = list(employees[(employees["city"] == city) &
                                         (employees["state"] == 'CA')]['employee_id'])

    loans_from_city = loans[(loans['employee_id'].isin(employees_from_city)) &
                            (loans['loan_type_id'] == 3)]

    dollar_value = loans_from_city['balance'].sum()

    if dollar_value != 0:
        city_value = city_value.append({'city': city, 
                                        'dollar_value': dollar_value}, 
                                        ignore_index=True)

print(city_value)

most_money = city_value['dollar_value'].max()
most_money_city = city_value[city_value['dollar_value'] == most_money]
print(most_money_city)