from bs4 import BeautifulSoup
import requests

def fetch_answer(test_id: str) -> str:
    url = 'https://phys-ege.sdamgia.ru/problem?id=' + test_id

    html = requests.get(url).text
    parser = BeautifulSoup(html, 'lxml')

    solution_block = parser.find('div', class_='solution')
    answer_block = solution_block.find_all('p')[-1].get_text()

    answer = 'Failed to fetch answer'
    if 'Ответ:' in answer_block:
        answer = answer_block[7:]

        if answer.endswith('.'):
            answer = answer[:-1]

        if answer == '':
                answer = 'Recieved answer as image'

    return answer



if __name__ == '__main__':
    url = 'https://physicsege.board.kettlec.tf/'

    html = requests.get(url).text
    parser = BeautifulSoup(html, 'lxml')

    id_block = parser.findAll('span', class_='s4')
    ids = []
    for id_ in id_block:
        ids += [id_.encode_contents().decode()]

    sum_ = 0
    for id_ in ids:
        answ = fetch_answer(id_)
        print(id_, answ)
        try:
            sum_ += int(answ)
        except ValueError:
            print(f"Id {id_} needs manual check")

    print(sum_)