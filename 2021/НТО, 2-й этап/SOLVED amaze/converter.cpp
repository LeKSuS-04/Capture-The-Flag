#include <fstream>
using namespace std;

bool check_number(uint64_t a1) {
    char *v2;
    char *v3;
    char v4;
    char *v5;
    uint64_t v6;
    int v7;

    v2 = (char *) malloc(64ULL);
    v3 = v2 + 63;
    do {
        v4 = a1;
        v5 = v3;
        a1 >>= 1;
        *v3-- = (v4 & 1) + 65;
    } while (v5 != v2);
    v6 = 0LL;
    v7 = 0;
    do {
        while (v2[v6] != 66 || v6 > 0x3D) {
            if (++v6 == 64) {
                free(v2);
                return v7 == 13;
            }
        }
        v7 += (((uint64_t)0xDFFFFB15DFFEF9DELL >> v6++) & 1) == 0;
    } while (v6 != 64);
    free(v2);
    return v7 == 13;
}

int main() {
    ifstream fin("numbers.txt");
    ofstream fout("good_bad.txt");

    uint64_t n;
    while (fin >> n) {
        bool res = check_number(n);
        fout << res << endl;
    }
}