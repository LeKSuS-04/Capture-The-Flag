from coollogs import log, Colors
import concurrent.futures
import json
import re
import requests
import sys
import time
from importlib import reload


config_filename = 'settings.json'

sleep_time = 0

attack_data = dict()
teams = dict()
tokens = dict()
exploits = dict()

submitted_flags = list()
submitting_flags_link = ''


def update_data():
    global attack_data, teams, tokens, sleep_time, exploits, submitting_flags_link

    try:
        with open(config_filename, 'r') as f:
            config_data = json.loads(f.read())

        sleep_time = config_data['sleep_time']

        attack_data = requests.get(config_data['attack_data_link']).json()
        submitting_flags_link = config_data['submitting_flags_link']

        teams = config_data['attack']
        tokens = config_data['tokens']

        for module_name in config_data['attacks']:
            if module_name in sys.modules.keys():
                reload(sys.modules[module_name])
            else:
                __import__(module_name)

            exploits[module_name] = sys.modules[module_name].attack
    except FileNotFoundError as e:
        log.error('Unable to find configuration file.')
        exit(0)


flag_regexp = re.compile(r'[A-Z0-9]{31}=')


def submit_flag(flag):
    points = 0
    for token in tokens:
        post_data = {'token': token, 'flag': flag}
        response = requests.post(submitting_flags_link, data=post_data).text

        if 'accepted' in response:
            log.plus(response)
            points += float(response.split(' ')[3].replace(',', '.'))
        else:
            log.error(response)

    return points / len(tokens)


def get_flags(text):
    flags = flag_regexp.findall(text)
    points = 0

    for flag in flags:
        if flag not in submitted_flags:
            points += submit_flag(flag)
            submitted_flags.append(flag)
            save_flag(flag)

    return points


def start_attack(attack_info):
    attack = attack_info['attack']
    log.info(
        f'Started {attack_info["attack_name"]} against team {attack_info["victim_name"]}'
    )

    try:
        data_with_flags = attack(attack_info['ip'], attack_data)
    except Exception as e:
        log.error(
            f'Error occurred while performing {attack_info["attack_name"]} against {attack_info["victim_name"]}; Details: {e}'
        )
        return

    points = 0
    if type(data_with_flags) is str:
        points = get_flags(data_with_flags)
    elif type(data_with_flags) is list:
        for data in data_with_flags:
            points += get_flags(data)
    else:
        log.error(
            f'Data returned from {attack_info["attack_name"]} against {attack_info["victim_name"]} is not list or string: it\'s {type(data_with_flags)}'
        )

    if points > 0:
        log.success(
            f'Ended {attack_info["attack_name"]} against team {attack_info["victim_name"]}; Retrieved {points} points'
        )
    else:
        log.custom(
            f'Ended {attack_info["attack_name"]} against team {attack_info["victim_name"]}; Retrieved {points} points',
            label_name='FAILURE',
            label_color=Colors.fg.red, # type: ignore
        )


def load_saved_flags():
    global submitted_flags
    with open('flags.txt', 'r') as f:
        loaded_flags = f.read().split('\n')

    for flag in loaded_flags:
        if flag != '':
            submitted_flags.append(flag)


def save_flag(flag):
    with open('flags.txt', 'a') as f:
        f.write(flag + '\n')


def main():
    while True:
        try:
            update_data()
            log.custom(f'Updated setting; starting attacks', label_name='START', label_color=Colors.fg.ORANGE)  # type: ignore

            attack_ip_pairs = list()
            for ip, victim_name in teams.items():
                for attack_name, attack in exploits.items():
                    attack_ip_pairs.append(
                        {
                            'ip': ip,
                            'victim_name': victim_name,
                            'attack': attack,
                            'attack_name': attack_name,
                        }
                    )

            with concurrent.futures.ThreadPoolExecutor() as executor:
                executor.map(start_attack, attack_ip_pairs)

            log.custom(f'Going to sleep for {sleep_time} seconds', label_name='SLEEP', label_color=Colors.fg.ORANGE)  # type: ignore
            time.sleep(sleep_time)

        except KeyboardInterrupt:
            log.critical('Ending program beacuse of KeyboardInterrupt')
            exit(0)
        except Exception as e:
            log.error(f'Unknown error occured: {e}')


if __name__ == '__main__':
    main()
