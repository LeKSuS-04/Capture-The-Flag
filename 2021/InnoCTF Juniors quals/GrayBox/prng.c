const char * prng(char *str, int seed) {
    size_t size = 40;
    for (size_t n = 0; n < size; n++) {
        int key = seed % 25;
        str[n] = (char)(65+key);
        seed = (seed * 5 + (seed - 9) + 6) % 300;
    }    
    return str;
}
