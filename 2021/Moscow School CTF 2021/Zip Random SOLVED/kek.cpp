#include <iostream>
#include <vector>
#include <time.h>
#include <stdlib.h>
using namespace std;

int main() {
    freopen("flag.enc", "r", stdin);

    uint8_t c;
    vector<uint8_t> enc, tmp;
    while (cin >> c) 
        enc.push_back(c);
    tmp.resize(enc.size());

    unsigned int t = time(0);
    while (t--) {
        copy(enc.begin(), enc.end(), tmp.begin());

        srand(t);
        for (int i = 0; i < tmp.size(); i++) {
            int v6 = rand();
            tmp[i] ^= v6 % 255;
        }

        if (tmp[0] == 'M' && tmp[1] == 'S' && tmp[2] == 'K') {
            for (int i = 0; i < tmp.size(); i++) 
                cout << (char) tmp[i];

            cout << endl;
            return 0;
        }

        if (t % 1000 == 0) cout << t << endl;
    }
}